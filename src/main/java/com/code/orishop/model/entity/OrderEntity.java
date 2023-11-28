package com.code.orishop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "`order`")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity  extends BaseEntity{

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address",columnDefinition = "nvarchar(MAX)")
    private String address;

    @Column(name = "customer_name",columnDefinition = "nvarchar(MAX)")
    private String customerName;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private UserEntity customer;

    @Column(name="status")
    int status = 0;

}
