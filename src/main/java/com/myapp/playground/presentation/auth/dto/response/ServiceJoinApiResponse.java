package com.myapp.playground.presentation.auth.dto.response;

import com.myapp.playground.presentation.auth.dto.TokenResponse;
import com.myapp.playground.presentation.auth.dto.JoinUserResponse;
import com.myapp.playground.application.auth.dto.response.AuthJoinResult;
import com.myapp.playground.application.auth.mapper.TokenMapper;
import com.myapp.playground.application.auth.mapper.UserMapper;

public record ServiceJoinApiResponse(
    JoinUserResponse user,
    TokenResponse token
) {

    public static ServiceJoinApiResponse from(AuthJoinResult result) {
        return new ServiceJoinApiResponse(
            JoinUserResponse.from(result.user()),
            TokenResponse.from(result.userToken())
        );
    }

}
