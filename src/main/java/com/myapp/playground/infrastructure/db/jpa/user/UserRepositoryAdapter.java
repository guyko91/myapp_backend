package com.myapp.playground.infrastructure.db.jpa.user;

import com.myapp.playground.domain.user.UserRepository;
import com.myapp.playground.domain.user.entity.User;
import com.myapp.playground.domain.user.entity.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
