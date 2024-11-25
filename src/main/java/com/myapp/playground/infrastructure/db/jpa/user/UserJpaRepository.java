package com.myapp.playground.infrastructure.db.jpa.user;

import com.myapp.playground.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
