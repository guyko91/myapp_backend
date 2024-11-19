package com.myapp.playground.application.auth.dto.response;

import com.myapp.playground.domain.user.entity.User;
import com.myapp.playground.domain.auth.entity.UserToken;

public record AuthJoinResult(
    User user,
    UserToken userToken
) { }
