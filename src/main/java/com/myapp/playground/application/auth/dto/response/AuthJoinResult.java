package com.myapp.playground.application.auth.dto.response;

import com.myapp.playground.application.auth.dto.TokenDto;
import com.myapp.playground.application.auth.dto.UserDto;
import com.myapp.playground.domain.auth.entity.User;
import com.myapp.playground.domain.auth.entity.UserToken;

public record AuthJoinResult(
    UserDto user,
    TokenDto userToken
) {
    public static AuthJoinResult of(User user, UserToken userToken) {
        return new AuthJoinResult(
            UserDto.from(user),
            TokenDto.from(userToken)
        );
    }
}
