package com.code.orishop.repository;

import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    CategoryEntity findByName(String name);
}
