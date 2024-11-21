package com.myapp.playground.application.auth.dto.request;

public record AuthJoinCommand(
    String oauthAuthorizationCode,
    String oauthRedirectUrl
) { }
