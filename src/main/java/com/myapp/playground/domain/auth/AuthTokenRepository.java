package com.myapp.playground.domain.auth;

import com.myapp.playground.domain.auth.entity.UserToken;

public interface AuthTokenRepository {

    UserToken saveToken(UserToken userToken);
    UserToken getToken(Long userId);
    void deleteToken(Long userId);

}