package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.user.AuthToken;
import java.time.LocalDateTime;

public record GoogleOAuthTokenRefreshResponse(
    String accessToken,
    int expiresIn,
    String scope,
    String tokenType
) {
    public AuthToken toDomainAuthToken() {
        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiresIn);
        return AuthToken.createWith(accessToken, null, expiresAt);
    }
}
