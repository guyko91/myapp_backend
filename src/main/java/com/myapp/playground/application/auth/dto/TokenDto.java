package com.myapp.playground.application.auth.dto;

import com.myapp.playground.domain.auth.entity.UserToken;

public record TokenDto(
    String accessToken,
    String refreshToken
) {
    public static TokenDto from(UserToken userToken) {
        return new TokenDto(
            userToken.getAccessToken(),
            userToken.getRefreshToken()
        );
    }
}
