package com.myapp.playground.application.auth.dto;

import com.myapp.playground.domain.auth.entity.User;

public record UserDto(
    String email,
    String name,
    String profileImageUrl
) {
    public static UserDto from(User user) {
        return new UserDto(
            user.getEmail(),
            user.getName(),
            user.getPicture()
        );
    }
}
