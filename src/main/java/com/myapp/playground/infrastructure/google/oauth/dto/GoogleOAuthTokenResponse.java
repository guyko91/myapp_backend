package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.user.AuthToken;
import java.time.LocalDateTime;

public record GoogleOAuthTokenResponse(
    String accessToken,
    String refreshToken,
    String scope,
    String tokenType,
    int expiresIn
) {
    public AuthToken toDomainAuthToken() {
        LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(expiresIn);
        return AuthToken.createWith(accessToken, refreshToken, expiredAt);
    }
}
