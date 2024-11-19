package com.myapp.playground.domain.user;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

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
        @AttributeOverride(name = "accessToken", column = @Column(name = "access_token")),
        @AttributeOverride(name = "refreshToken", column = @Column(name = "refresh_token")),
        @AttributeOverride(name = "expiresAt", column = @Column(name = "expires_at"))
    })
    private AuthToken token;

    protected UserAuth() { }

    private UserAuth(Long id, User user, AuthType type, AuthToken token) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.token = token;
    }

    public static UserAuth createWith(User user, AuthType type, AuthToken token) {
        return new UserAuth(null, user, type, token);
    }

}
