package com.myapp.playground.domain.auth.entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@Embeddable
public class OAuthToken {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;

    // 토큰 발급 시, Oauth token 발급처에서 제공하는 만료시간과의 차이를 고려하여 만료시간에 마진을 둔다.
    private static final int EXPIRE_SECOND_MARGIN = 10;

    protected OAuthToken() { }

    private OAuthToken(String accessToken, String refreshToken, LocalDateTime expiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public static OAuthToken createWith(String accessToken, String refreshToken, LocalDateTime expiresAt) {
        return new OAuthToken(accessToken, refreshToken, expiresAt);
    }

    public OAuthToken withNewAccessToken(String accessToken, int expiresIn) {
        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiresIn);
        return new OAuthToken(accessToken, refreshToken, expiresAt);
    }

    public boolean isNotExpired() {
        return expiresAt.minusSeconds(EXPIRE_SECOND_MARGIN).isAfter(LocalDateTime.now());
    }

}
