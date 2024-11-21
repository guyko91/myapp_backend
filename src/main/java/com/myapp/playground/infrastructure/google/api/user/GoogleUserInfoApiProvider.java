package com.myapp.playground.infrastructure.google.api.user;

import com.myapp.playground.domain.auth.OAuthUserInfoClient;
import com.myapp.playground.domain.auth.entity.OAuthToken;
import com.myapp.playground.domain.auth.entity.User;
import com.myapp.playground.infrastructure.google.config.GoogleApi;
import com.myapp.playground.infrastructure.google.config.GoogleOAuth2Properties;
import com.myapp.playground.infrastructure.google.exception.GoogleOAuthException;
import com.myapp.playground.infrastructure.google.oauth.dto.GoogleOAuthUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GoogleUserInfoApiProvider implements OAuthUserInfoClient {

    private final WebClient googleApiWebClient;
    private final GoogleOAuth2Properties properties;

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

    private GoogleOAuthUserResponse requestUserInfoWith(OAuthToken token) {
        return googleApiWebClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(GoogleApi.USER_API.getUri())
                .queryParam("access_token", token.getAccessToken())
                .build()
            )
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                clientResponse -> clientResponse.bodyToMono(String.class).map(GoogleOAuthException::new))
            .bodyToMono(GoogleOAuthUserResponse.class)
            .block();
    }
}
