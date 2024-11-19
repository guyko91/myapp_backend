package com.myapp.playground.domain.user;

import com.myapp.playground.domain.user.entity.OAuthToken;
import com.myapp.playground.domain.user.entity.User;

public interface OauthUseCase {

    OAuthToken getTokenInfoBy(String authorizationCode, String redirectUri);
    User getUserInfoBy(OAuthToken token);
    OAuthToken refreshToken(OAuthToken token);

}
