package com.code.orishop.repository;

import com.code.orishop.model.Chart.ProductHot;
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

    @Query("SELECT p FROM ProductEntity p " +
            " WHERE p.id NOT IN " +
            "( SELECT od.product.id " +
            " FROM OrderDetailEntity od " +
            " WHERE od.order.id = :orderId ) And p.status= true ")
    List<ProductEntity> findAllProductNotInOrder(@Param("orderId") Long orderId);

    @Query("SELECT new com.code.orishop.model.Chart.ProductHot(od.product, SUM(od.quantity)) " +
            "FROM OrderDetailEntity od " +
            "GROUP BY od.product " +
            "ORDER BY SUM(od.quantity) DESC limit 5")
    List<ProductHot> findTop5SellingProducts();

    @Query("SELECT p FROM ProductEntity p WHERE p.name like %:name%")
    List<ProductEntity> searchAllByName(@Param("name") String name);
}
