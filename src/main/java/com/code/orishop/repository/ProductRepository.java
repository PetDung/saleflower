package com.code.orishop.repository;

import com.code.orishop.model.Chart.ProductHot;
import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    ProductEntity findByName(String name);
    @Query( " select p from ProductEntity  p where p.category.id <> :categoryId ")
    List<ProductEntity> findAllProductsNotCategories(@Param("categoryId") Long categoryId);

    @Query( " select p from ProductEntity p ")
    Page<ProductEntity> findAllByPage(Pageable pageable);


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
            " where od.product.status = true " +
            "GROUP BY od.product " +
            "ORDER BY SUM(od.quantity) DESC limit  :top ")
    List<ProductHot> findTopSellingProducts(@Param("top") int top);

    @Query("SELECT od.product " +
            "FROM OrderDetailEntity od " +
            " where od.product.status = true " +
            "GROUP BY od.product " +
            "ORDER BY SUM(od.quantity) DESC limit  :top ")
    List<ProductEntity> findTopTrendProducts(@Param("top") int top);

    @Query("SELECT p FROM ProductEntity p " +
            " where p.status = true  " +
            " ORDER BY p.createdAt DESC")
    List<ProductEntity> sortByCreated();



    @Query("SELECT p FROM ProductEntity p WHERE p.name like %:name%")
    List<ProductEntity> searchAllByName(@Param("name") String name);

    @Query(" SELECT SUM(od.quantity) FROM OrderDetailEntity od WHERE od.product.id = :id ")
    Long getQuantitySold (@Param("id") Long id);
}
