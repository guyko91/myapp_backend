package com.myapp.playground.presentation.auth.dto;

import com.myapp.playground.application.auth.dto.UserDto;

public record JoinUserResponse(
    String email,
    String name,
    String picture
) {
    public static JoinUserResponse from(UserDto userDto) {
        return new JoinUserResponse(
            userDto.email(),
            userDto.name(),
            userDto.profileImageUrl()
        );
    }
}
