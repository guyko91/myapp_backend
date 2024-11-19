package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.infrastructure.google.config.GoogleOAuth2Properties;

public record GoogleOAuthTokenRefreshRequest(
    String clientId,
    String clientSecret,
    String refreshToken,
    String grantType
) {

    private static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    public static GoogleOAuthTokenRefreshRequest of(String refreshToken, GoogleOAuth2Properties properties) {
        return new GoogleOAuthTokenRefreshRequest(
            properties.getClientId(),
            properties.getClientSecret(),
            refreshToken,
            REFRESH_TOKEN_GRANT_TYPE
        );
    }
}
