package com.code.orishop.repository;

import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    ProductEntity findByName(String name);
    @Query( " select p from ProductEntity  p where p.category.id <> :categoryId ")
    List<ProductEntity> findAllProductsNotCategories(@Param("categoryId") Long categoryId);

    @Query( " select p from ProductEntity  p where p.brand.id <> :brandId ")
    List<ProductEntity> findAllProductsNotBrand(@Param("brandId") Long brandId);
}
