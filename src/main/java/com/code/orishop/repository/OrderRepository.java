package com.code.orishop.repository;

import com.code.orishop.model.Chart.Data;
import com.code.orishop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository  extends JpaRepository<OrderEntity,Long> {

    @Query("SELECT new com.code.orishop.model.Chart.Data(MONTH(o.createdAt), SUM(o.totalAmount)) " +
            "FROM OrderEntity o " +
            "WHERE YEAR(o.createdAt) = :year AND MONTH(o.createdAt) = :month " +
            "GROUP BY MONTH(o.createdAt)")
    Data getRevenueByMonth(@Param("year") int year, @Param("month") int month);

    OrderEntity findByCustomer_IdAndStatus(Long id,int status);
}
