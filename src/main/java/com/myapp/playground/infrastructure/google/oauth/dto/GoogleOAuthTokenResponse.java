package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.user.entity.OAuthToken;
import java.time.LocalDateTime;

public record GoogleOAuthTokenResponse(
    String access_token,
    String refresh_token,
    String scope,
    String token_type,
    int expires_in
) {
    public OAuthToken toDomainAuthToken() {
        LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(expires_in);
        return OAuthToken.createWith(access_token, refresh_token, expiredAt);
    }
}
