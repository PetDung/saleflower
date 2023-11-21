package com.code.orishop.service;

import com.code.orishop.model.entity.OrderEntity;
import com.code.orishop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<OrderEntity> getAll(){
        return orderRepository.findAll();
    }

    public OrderEntity getById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->{
                    try {
                        throw new Exception(("Not found!"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
