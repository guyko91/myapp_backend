package com.myapp.playground.domain.auth.entity;

import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("UserToken")
public class UserToken implements Serializable {

    @Id
    private String userId;
    private String accessToken;
    private String refreshToken;
    private long expiration;

    protected UserToken() { }

    private UserToken(String userId, String accessToken, String refreshToken, long expiration) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
