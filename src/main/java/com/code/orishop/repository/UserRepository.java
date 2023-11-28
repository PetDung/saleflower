package com.code.orishop.repository;

import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserName(String userName);

    @Query("  SELECT u FROM UserEntity u " +
            " JOIN u.roles r " +
            " WHERE u.userName LIKE %:userName% " +
            " AND LOWER(r.roleName) = 'customer' ")
    List<UserEntity> findAllCustomersByUserName(@Param("userName") String userName);

    Optional<UserEntity> findByEmail(String email);

}
