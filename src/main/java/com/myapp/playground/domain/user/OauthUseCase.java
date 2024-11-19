package com.myapp.playground.domain.user;

public interface OauthUseCase {

    AuthToken getTokenInfoBy(String authorizationCode);
    User getUserInfoBy(AuthToken token);
    AuthToken refreshToken(AuthToken token);

}
