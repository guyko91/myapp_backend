package com.myapp.playground.domain.auth;

import com.myapp.playground.domain.auth.entity.OAuthToken;
import com.myapp.playground.domain.auth.entity.User;

public interface OAuthUserInfoClient {
    User getUserInfoBy(OAuthToken token);
}
