package com.myapp.playground.domain.user;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@Embeddable
public class AuthToken {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;

    // 토큰 발급 시, Oauth token 발급처에서 제공하는 만료시간과의 차이를 고려하여 만료시간에 마진을 둔다.
    private static final int EXPIRE_SECOND_MARGIN = 10;

    protected AuthToken() { }

    private AuthToken(String accessToken, String refreshToken, LocalDateTime expiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public static AuthToken createWith(String accessToken, String refreshToken, LocalDateTime expiresAt) {
        return new AuthToken(accessToken, refreshToken, expiresAt);
    }

    public AuthToken withNewAccessToken(String accessToken, int expiresIn) {
        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiresIn);
        return new AuthToken(accessToken, refreshToken, expiresAt);
    }

    public boolean isNotExpired() {
        return expiresAt.minusSeconds(EXPIRE_SECOND_MARGIN).isAfter(LocalDateTime.now());
    }

}
