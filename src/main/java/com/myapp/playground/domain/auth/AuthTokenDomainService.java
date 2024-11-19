package com.myapp.playground.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthTokenDomainService {

    private final AuthTokenRepository authTokenRepository;

    // TODO 토큰 생성

}
