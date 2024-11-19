package com.myapp.playground.infrastructure.google.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component @Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
public class GoogleOAuth2Properties {

    private String clientId;
    private String clientSecret;
    private String redirectUri;

}
