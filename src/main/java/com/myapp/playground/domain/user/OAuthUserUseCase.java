package com.myapp.playground.domain.user;

import com.myapp.playground.domain.user.entity.OAuthToken;
import com.myapp.playground.domain.user.entity.User;

public interface OAuthUserUseCase {
    User getUserInfoBy(OAuthToken token);
}
