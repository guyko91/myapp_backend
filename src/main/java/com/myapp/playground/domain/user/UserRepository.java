package com.myapp.playground.domain.user;

import com.myapp.playground.domain.user.entity.User;
import com.myapp.playground.domain.user.entity.UserAuth;

public interface UserRepository {
    User saveUser(User user);
    UserAuth saveUserAuth(UserAuth userAuth);
}
