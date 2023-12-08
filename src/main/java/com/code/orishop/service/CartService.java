package com.code.orishop.service;

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
import java.util.Objects;

@Service
public class CartService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    public void add(Integer quantity, Long idProduct, Long idUser) throws Exception {
        UserEntity user = userService.getById(idUser);

        OrderEntity order = orderRepository.findByCustomer_IdAndStatus(idUser,-1);
        List<OrderDetailEntity> orderDetailEntities;
        Double total = 0.0;
        if(order == null){
            order = new OrderEntity();
            orderDetailEntities =new ArrayList<>();
        }else{
            orderDetailEntities = order.getOrderDetails();
            total = order.getTotalAmount();
        }

        ProductEntity product = productService.getProductById(idProduct);
        for(int i = 0; i< orderDetailEntities.size(); i++){
            ProductEntity p = orderDetailEntities.get(i).getProduct();
            if(Objects.equals(p.getId(), idProduct)){
                total = total + (quantity*product.getPrice()) - orderDetailEntities.get(i).getUnitPrice();
                orderDetailEntities.get(i).setQuantity(quantity);
                orderDetailEntities.get(i).setUnitPrice(quantity*product.getPrice());
                orderDetailRepository.save( orderDetailEntities.get(i));
                orderRepository.save(order);
                return;
            }
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

        order.setOrderDetails(orderDetailEntities);
        order.setCustomer(user);
        order.setTotalAmount(total);
        order.setStatus(-1);
        orderRepository.save(order);

        user.getOrders().add(order);
        userRepository.save(user);

        for(int i = 0; i< orderDetailEntities.size(); i++){
            orderDetailEntities.get(i).setOrder(order);
            orderDetailRepository.save(orderDetailEntities.get(i));
        }
    }
    public OrderEntity getCartByIdUser(Long idUser){
        UserEntity user = userService.getById(idUser);
        return orderRepository.findByCustomer_IdAndStatus(idUser,-1);
    }
}
