package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.user.entity.OAuthToken;
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
