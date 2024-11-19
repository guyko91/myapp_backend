package com.myapp.playground.presentation.auth.dto.response;

import com.myapp.playground.application.auth.dto.response.AuthJoinResult;
import com.myapp.playground.presentation.common.mapper.TokenMapper;
import com.myapp.playground.presentation.common.mapper.UserMapper;

public record ServiceJoinResponse(
    UserResponse user,
    TokenResponse token
) {

    public static ServiceJoinResponse from(AuthJoinResult result) {
        return new ServiceJoinResponse(
            UserMapper.INSTANCE.toResponse(result.user()),
            TokenMapper.INSTANCE.toResponse(result.userToken())
        );
    }

}
