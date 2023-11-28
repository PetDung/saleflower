package com.code.orishop.service;

import com.code.orishop.model.Chart.Data;
import com.code.orishop.model.entity.OrderDetailEntity;
import com.code.orishop.model.entity.OrderEntity;
import com.code.orishop.model.entity.ProductEntity;
import com.code.orishop.model.entity.UserEntity;
import com.code.orishop.model.request.OrderDetailRes;
import com.code.orishop.model.request.OrderRes;
import com.code.orishop.repository.OrderDetailRepository;
import com.code.orishop.repository.OrderRepository;
import com.code.orishop.repository.ProductRepository;
import com.code.orishop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

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

    public void create(OrderRes orderRes) throws Exception {
        UserEntity user = userRepository.findById(orderRes.getOrderBy())
                .orElse(userRepository.findByUserName("default"));
        Double total = 0.0;
        List<OrderDetailEntity> orderDetailEntities =new ArrayList<>();
        for (int i = 0; i < orderRes.getOrderDetalList().size(); i++) {
            OrderDetailRes od = orderRes.getOrderDetalList().get(i);
            ProductEntity product = productService.getProductById(od.getProductId());
            Integer quantity = od.getQuantity();
            if(quantity < product.getQuantityInStock()){
                product.setQuantityInStock(product.getQuantityInStock() - quantity);
            }else{
                quantity = product.getQuantityInStock();
                product.setQuantityInStock(0);

            }
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setQuantity(quantity);
            orderDetail.setProduct(product);
            orderDetail.setUnitPrice(quantity * product.getPrice());
            orderDetailRepository.save(orderDetail);
            product.getOrderDetails().add(orderDetail);
            productRepository.save(product);
            orderDetailEntities.add(orderDetail);
            total +=orderDetail.getUnitPrice();
        }

        OrderEntity order = OrderEntity.builder()
                .orderDetails(orderDetailEntities)
                .address(orderRes.getAddress())
                .phone(orderRes.getPhone())
                .customer(user)
                .customerName(orderRes.getCustomerName())
                .totalAmount(total)
                .build();
        orderRepository.save(order);
        user.getOrders().add(order);
        userRepository.save(user);
        for(int i = 0; i< orderDetailEntities.size(); i++){
            orderDetailEntities.get(i).setOrder(order);
            orderDetailRepository.save(orderDetailEntities.get(i));
        }
    }
    public void update(OrderRes orderRes,Long id) throws Exception {
        OrderEntity  entity =getById(id);
        List<OrderDetailEntity> oldOrderDetailEntities =new ArrayList<>();
        entity.getOrderDetails().forEach(od -> {
            ProductEntity productEntity =od.getProduct();
            productEntity.setQuantityInStock(od.getQuantity() + productEntity.getQuantityInStock());
            productRepository.save(productEntity);
            oldOrderDetailEntities.add(od);
        });
        entity.getOrderDetails().clear();
        orderDetailRepository.deleteAll(oldOrderDetailEntities);
        orderRepository.save(entity);
        double total = 0.0;


        List<OrderDetailEntity> orderDetailEntities =new ArrayList<>();
        for (int i = 0; i < orderRes.getOrderDetalList().size(); i++) {
            OrderDetailRes od = orderRes.getOrderDetalList().get(i);
            ProductEntity product = productService.getProductById(od.getProductId());
            Integer quantity = od.getQuantity();
            if(quantity < product.getQuantityInStock()){
                product.setQuantityInStock(product.getQuantityInStock() - quantity);
            }else{
                quantity = product.getQuantityInStock();
                product.setQuantityInStock(0);

            }
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setQuantity(quantity);
            orderDetail.setProduct(product);
            orderDetail.setUnitPrice(quantity * product.getPrice());
            orderDetailRepository.save(orderDetail);
            product.getOrderDetails().add(orderDetail);
            productRepository.save(product);
            orderDetailEntities.add(orderDetail);
            total +=orderDetail.getUnitPrice();
        }
        entity.setOrderDetails(orderDetailEntities);
        entity.setCustomerName(orderRes.getCustomerName());
        entity.setAddress(orderRes.getAddress());
        entity.setTotalAmount(total);
        orderRepository.save(entity);
        for(int i = 0; i< orderDetailEntities.size(); i++){
            orderDetailEntities.get(i).setOrder(entity);
            orderDetailRepository.save(orderDetailEntities.get(i));
        }
    }
    public void remove(Long id){
        OrderEntity order = getById(id);

        orderDetailRepository.deleteAll(order.getOrderDetails());
        order.getOrderDetails().clear();
        orderRepository.delete(order);
    }

    public List<Data> getRevenueByMonth(int year) {
        List<Data> result = new ArrayList<>();
        for(int i= 1 ; i<= 12 ; i++){
            Data data = orderRepository.getRevenueByMonth(year, i);
            if(data == null){
                data = new Data();
                data.setMonth(i);
                data.setRevenue(0.0);
            }
            result.add(data);
        }
        return result;
    }
}
