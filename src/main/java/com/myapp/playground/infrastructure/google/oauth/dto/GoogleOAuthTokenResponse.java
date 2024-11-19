package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.user.entity.OAuthToken;
import java.time.LocalDateTime;

public record GoogleOAuthTokenResponse(
    String accessToken,
    String refreshToken,
    String scope,
    String tokenType,
    int expiresIn
) {
    public OAuthToken toDomainAuthToken() {
        LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(expiresIn);
        return OAuthToken.createWith(accessToken, refreshToken, expiredAt);
    }
}
