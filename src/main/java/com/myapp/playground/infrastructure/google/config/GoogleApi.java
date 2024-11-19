package com.myapp.playground.infrastructure.google.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GoogleApi {

    TOKEN_API("토큰 요청 API", "token"),
    USER_API("사용자 정보 요청 API", "/oauth2/v1/userinfo"),
    CALENDAR_API("구글 캘린더 API", "/v1/userinfo"),
    ;

    private final String description;
    private final String uri;

}
