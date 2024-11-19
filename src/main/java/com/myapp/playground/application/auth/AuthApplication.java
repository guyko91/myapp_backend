package com.myapp.playground.application.auth;

import com.myapp.playground.application.auth.dto.request.AuthJoinCommand;
import com.myapp.playground.application.auth.dto.response.AuthJoinResult;
import com.myapp.playground.domain.auth.AuthTokenDomainService;
import com.myapp.playground.domain.user.UserDomainService;
import com.myapp.playground.domain.user.dto.response.UserJoinResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthApplication {

    private final UserDomainService userService;
    private final AuthTokenDomainService authTokenService;

    @Transactional
    public AuthJoinResult join(AuthJoinCommand command) {

        // 사용자 정보 생성 (with OAuth)
        UserJoinResult userJoinResult = userService.joinOAuthUser(command.toDomainCommand());

        // TODO 캘린더 정보 생성

        // TODO 토큰 정보 생성

        return new AuthJoinResult(userJoinResult.user(), null);
    }

    // TODO 로그인

}
