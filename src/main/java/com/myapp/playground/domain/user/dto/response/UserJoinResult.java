package com.myapp.playground.domain.user.dto.response;

import com.myapp.playground.domain.user.entity.User;
import com.myapp.playground.domain.user.entity.UserAuth;

public record UserJoinResult(
    User user,
    UserAuth userAuth
) { }
