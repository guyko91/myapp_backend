package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.auth.entity.OAuthToken;
import java.time.LocalDateTime;

public record GoogleOAuthTokenRefreshResponse(
    String accessToken,
    int expiresIn,
    String scope,
    String tokenType
) {
    public OAuthToken toDomainAuthToken() {
        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiresIn);
        return OAuthToken.createWith(accessToken, null, expiresAt);
    }
}
