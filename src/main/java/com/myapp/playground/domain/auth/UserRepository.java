package com.myapp.playground.domain.auth;

import com.myapp.playground.domain.auth.entity.User;
import com.myapp.playground.domain.auth.entity.UserAuth;

public interface UserRepository {
    User saveUser(User user);
    UserAuth saveUserAuth(UserAuth userAuth);
}
