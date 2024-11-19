package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.infrastructure.google.config.GoogleOAuth2Properties;

public record GoogleOAuthTokenRequest(
    String code,
    String redirectUri,
    String clientId,
    String clientSecret,
    String grantType,
    String accessType
) {

    private static final String GRANT_TYPE = "authorization_code";
    private static final String ACCESS_TYPE = "offline";

    public static GoogleOAuthTokenRequest of(String code, String redirectUrl, GoogleOAuth2Properties oAuth2Properties) {
        return new GoogleOAuthTokenRequest(
            code,
            redirectUrl,
            oAuth2Properties.getClientId(),
            oAuth2Properties.getClientSecret(),
            GRANT_TYPE,
            ACCESS_TYPE
        );
    }

}
