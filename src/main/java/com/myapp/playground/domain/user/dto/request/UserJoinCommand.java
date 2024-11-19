package com.myapp.playground.domain.user.dto.request;

public record UserJoinCommand(
    String authorizationCode,
    String redirectUri
) { }
