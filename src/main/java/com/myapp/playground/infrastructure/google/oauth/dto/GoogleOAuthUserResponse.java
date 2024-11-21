package com.myapp.playground.infrastructure.google.oauth.dto;

import com.myapp.playground.domain.auth.entity.User;

public record GoogleOAuthUserResponse(
    String id,
    String email,
    String name,
    String picture
) {
    public User toDomainUser() {
        return User.createWith(email, name, picture);
    }
}
