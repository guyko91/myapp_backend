package com.myapp.playground.presentation.auth.dto.response;

public record TokenResponse(
    String accessToken,
    String refreshToken
) { }
