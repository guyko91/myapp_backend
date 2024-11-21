package com.myapp.playground.application.auth;

import com.myapp.playground.application.auth.dto.request.AuthJoinCommand;
import com.myapp.playground.application.auth.dto.response.AuthJoinResult;
import com.myapp.playground.domain.auth.AuthDomainService;
import com.myapp.playground.domain.auth.entity.User;
import com.myapp.playground.domain.auth.entity.UserToken;
import com.myapp.playground.domain.calendar.CalendarDomainService;
import com.myapp.playground.domain.calendar.entity.UserCalendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthApplication {

    private final AuthDomainService authDomainService;
    private final CalendarDomainService calendarDomainService;

    @Transactional
    public AuthJoinResult join(AuthJoinCommand command) {

        // 사용자 인증 정보로 사용자 생성
        String oauthAuthorizationCode = command.oauthAuthorizationCode();
        String oauthRedirectUrl = command.oauthRedirectUrl();
        User savedUser = authDomainService.joinUserWithOAuth(oauthAuthorizationCode, oauthRedirectUrl);

        // 캘린더 정보 생성
        Long userId = savedUser.getId();
        String userEmail = savedUser.getEmail();
        UserCalendar userCalendar = calendarDomainService.createUserCalendar(userId, userEmail);

        // 토큰 정보 생성
        UserToken userToken = authDomainService.createToken(savedUser);

        return AuthJoinResult.of(savedUser, userToken);
    }

}
