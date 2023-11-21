package com.code.orishop.repository;

import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserName(String userName);

    Optional<UserEntity> findByEmail(String email);
}
