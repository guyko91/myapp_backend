package com.myapp.playground.presentation.auth.dto;

import com.myapp.playground.application.auth.dto.TokenDto;

public record TokenResponse(
    String accessToken,
    String refreshToken
) {
    public static TokenResponse from(TokenDto tokenDto) {
        return new TokenResponse(
            tokenDto.accessToken(),
            tokenDto.refreshToken()
        );
    }
}
