package com.code.orishop.repository;

import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository  extends JpaRepository<OrderEntity,Long> {
}
