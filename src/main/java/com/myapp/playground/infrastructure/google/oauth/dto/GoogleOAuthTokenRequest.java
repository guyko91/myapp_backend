package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.infrastructure.google.config.GoogleOAuth2Properties;

public record GoogleOAuthTokenRequest(
    String code,
    String clientId,
    String clientSecret,
    String redirectUri,
    String grantType,
    String accessType
) {

    private static final String GRANT_TYPE = "authorization_code";
    private static final String ACCESS_TYPE = "offline";

    public static GoogleOAuthTokenRequest of(String code, GoogleOAuth2Properties oAuth2Properties) {
        return new GoogleOAuthTokenRequest(
            code,
            oAuth2Properties.getClientId(),
            oAuth2Properties.getClientSecret(),
            oAuth2Properties.getRedirectUri(),
            GRANT_TYPE,
            ACCESS_TYPE
        );
    }

}
