package com.myapp.playground.infrastructure.db.redis.auth;

import com.myapp.playground.domain.auth.TokenRepository;
import com.myapp.playground.domain.auth.entity.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepository {

    private final UserTokenRepository userTokenRepository;

    @Override
    public UserToken saveToken(UserToken userToken) {
        return null;
    }

    @Override
    public UserToken getToken(Long userId) {
        return null;
    }

    @Override
    public void deleteToken(Long userId) {

    }
}
