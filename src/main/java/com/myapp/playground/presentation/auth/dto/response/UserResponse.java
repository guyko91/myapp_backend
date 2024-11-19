package com.myapp.playground.presentation.auth.dto.response;

public record UserResponse(
    long id,
    String email,
    String name,
    String picture
) { }
