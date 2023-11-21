package com.code.orishop.repository;

import com.code.orishop.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity,Long> {

    BrandEntity findByName(String name);
}
