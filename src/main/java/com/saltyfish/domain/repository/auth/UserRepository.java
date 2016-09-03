package com.saltyfish.domain.repository.auth;

import com.saltyfish.domain.entity.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weck on 16/8/31.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(@Param("id") Integer id);

    UserEntity findByName(@Param("name") String name);
}