package com.myapp.playground.application.auth.dto.request;

import com.myapp.playground.domain.user.dto.request.UserJoinCommand;

public record AuthJoinCommand(
    String oauthAuthorizationCode,
    String oauthRedirectUrl
) {

    public UserJoinCommand toDomainCommand() {
        return new UserJoinCommand(oauthAuthorizationCode(), oauthRedirectUrl());
    }
}
