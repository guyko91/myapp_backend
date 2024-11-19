package com.myapp.playground.infrastructure.db.jpa.user;

import com.myapp.playground.domain.user.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthJpaRepository extends JpaRepository<UserAuth, Long> {

}
