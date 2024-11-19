package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.user.AuthToken;

public record GoogleOAuthTokenRefreshResponse(
    String accessToken,
    int expiresIn,
    String scope,
    String tokenType
) {
    public AuthToken toDomainAuthToken() {
        return AuthToken.createWith(accessToken, null, expiresIn);
    }
}
