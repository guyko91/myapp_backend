package com.myapp.playground.infrastructure.db.jpa.user;

import com.myapp.playground.domain.auth.UserRepository;
import com.myapp.playground.domain.auth.entity.User;
import com.myapp.playground.domain.auth.entity.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserAuthJpaRepository userAuthJpaRepository;

    @Override
    public User saveUser(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public UserAuth saveUserAuth(UserAuth userAuth) {
        return userAuthJpaRepository.save(userAuth);
    }
}
