package com.myapp.playground.infrastructure.google.oauth;

import com.myapp.playground.domain.user.entity.OAuthToken;
import com.myapp.playground.domain.user.entity.User;
import com.myapp.playground.domain.user.OauthUseCase;
import com.myapp.playground.infrastructure.google.config.GoogleApi;
import com.myapp.playground.infrastructure.google.config.GoogleOAuth2Properties;
import com.myapp.playground.infrastructure.google.oauth.dto.GoogleOAuthTokenRefreshRequest;
import com.myapp.playground.infrastructure.google.oauth.dto.GoogleOAuthTokenRefreshResponse;
import com.myapp.playground.infrastructure.google.oauth.dto.GoogleOAuthTokenRequest;
import com.myapp.playground.infrastructure.google.oauth.dto.GoogleOAuthTokenResponse;
import com.myapp.playground.infrastructure.google.exception.GoogleOAuthException;
import com.myapp.playground.infrastructure.google.oauth.dto.GoogleOAuthUserResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
class GoogleAuthProvider implements OauthUseCase {

    private final WebClient oauthWebClient;
    private final GoogleOAuth2Properties properties;

    /**
     * Client 에서 구글 로그인 연동 후 받은 authorizationCode 를 이용하여 구글 OAuth 토큰을 요청합니다.
     * @param authorizationCode : Client 에서 받은 authorizationCode
     * @return AuthToken : 토큰 도메인 객체
     */
    @Override
    public OAuthToken getTokenInfoBy(String authorizationCode, String redirectUri) {
        var decodedAuthCode = decodeAuthorizationCode(authorizationCode);
        var request = GoogleOAuthTokenRequest.of(decodedAuthCode, redirectUri, properties);

        GoogleOAuthTokenResponse response;
        try {
            response = requestTokenInfoWith(request);
            return response.toDomainAuthToken();
        }catch (Exception e) {
            String exceptionMessage = String.format("구글 OAuth 토큰 요청에 실패했습니다. %s", e.getMessage());
            throw new GoogleOAuthException(exceptionMessage);
        }
    }

    /**
     * 구글 OAuth 토큰을 이용하여 사용자 정보를 요청합니다.
     * @param token : 토큰 도메인 객체
     * @return User : 사용자 도메인 객체
     */
    @Override
    public User getUserInfoBy(OAuthToken token) {
        var userInfoResponse = requestUserInfoWith(token);
        return userInfoResponse.toDomainUser();
    }

    /**
     * 토큰이 만료되었을 경우, refreshToken 을 이용하여 새로운 토큰을 발급받습니다.
     * 만료되지 않은 토큰은 그대로 반환합니다.
     * @param token : 토큰 도메인 객체 (만료된)
     * @return AuthToken : 새로 발급받은 토큰 도메인 객체
     */
    @Override
    public OAuthToken refreshToken(OAuthToken token) {
        if (token.isNotExpired()) { return token; }

        String refreshToken = token.getRefreshToken();
        var refreshTokenRequest = GoogleOAuthTokenRefreshRequest.of(refreshToken, properties);

        var refreshResponse = requestTokenRefreshWith(refreshTokenRequest);
        var newAccessToken = refreshResponse.accessToken();
        var expiresIn = refreshResponse.expiresIn();

        return token.withNewAccessToken(newAccessToken, expiresIn);
    }

    private String decodeAuthorizationCode(String authorizationCode) {
        return URLDecoder.decode(authorizationCode, StandardCharsets.UTF_8);
    }

    private GoogleOAuthTokenResponse requestTokenInfoWith(GoogleOAuthTokenRequest request) {
        return oauthWebClient.post()
            .uri(GoogleApi.TOKEN_API.getUri())
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
            clientResponse -> clientResponse.bodyToMono(String.class).map(GoogleOAuthException::new))
            .bodyToMono(GoogleOAuthTokenResponse.class)
            .block();
    }

    private GoogleOAuthUserResponse requestUserInfoWith(OAuthToken token) {
        return oauthWebClient.get()
            .uri(GoogleApi.USER_API.getUri())
            .header("Authorization", getAuthorizationHeaderValueWith(token))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
            clientResponse -> clientResponse.bodyToMono(String.class).map(GoogleOAuthException::new))
            .bodyToMono(GoogleOAuthUserResponse.class)
            .block();
    }

    private GoogleOAuthTokenRefreshResponse requestTokenRefreshWith(GoogleOAuthTokenRefreshRequest request) {
        return oauthWebClient.post()
            .uri(GoogleApi.TOKEN_API.getUri())
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
            clientResponse -> clientResponse.bodyToMono(String.class).map(GoogleOAuthException::new))
            .bodyToMono(GoogleOAuthTokenRefreshResponse.class)
            .block();
    }

    private String getAuthorizationHeaderValueWith(OAuthToken token) {
        return "Bearer " + token.getAccessToken();
    }
}
