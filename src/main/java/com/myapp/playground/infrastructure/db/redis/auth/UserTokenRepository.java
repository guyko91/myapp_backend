package com.myapp.playground.infrastructure.db.redis.auth;

import com.myapp.playground.domain.auth.entity.UserToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

}
