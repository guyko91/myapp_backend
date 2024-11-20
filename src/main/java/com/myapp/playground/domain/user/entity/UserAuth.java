package com.myapp.playground.domain.user.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.experimental.Accessors;

@Entity
@Table(name = "tb_user_auth")
public class UserAuth {

    @Id
    @Column(name = "id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AuthType type;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "access_token", column = @Column(name = "access_token")),
        @AttributeOverride(name = "refresh_token", column = @Column(name = "refresh_token")),
        @AttributeOverride(name = "expiresAt", column = @Column(name = "expires_at"))
    })
    private OAuthToken token;

    protected UserAuth() { }

    private UserAuth(Long id, User user, AuthType type, OAuthToken token) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.token = token;
    }

    public static UserAuth createWith(User user, AuthType type, OAuthToken token) {
        return new UserAuth(null, user, type, token);
    }

}
