package com.myapp.playground.presentation.auth.dto.request;

import com.myapp.playground.application.auth.dto.request.AuthJoinCommand;

public record ServiceJoinRequest(
    String authorizationCode,
    String redirectUrl
) {
    public AuthJoinCommand toJoinCommand() {
        return new AuthJoinCommand(authorizationCode(), redirectUrl());
    }
}
