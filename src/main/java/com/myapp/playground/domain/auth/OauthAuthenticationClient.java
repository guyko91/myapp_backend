package com.myapp.playground.domain.auth;

import com.myapp.playground.domain.auth.entity.OAuthToken;

public interface OauthAuthenticationClient {

    OAuthToken getTokenInfoBy(String authorizationCode, String redirectUri);
    OAuthToken refreshToken(OAuthToken token);

}
