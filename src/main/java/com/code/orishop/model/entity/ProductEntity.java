package com.code.orishop.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity  extends BaseEntity{

    @Column(name = "name",columnDefinition = "nvarchar(MAX)")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @Column(name = "status")
    private Boolean status =true;

    @Column(name = "description",columnDefinition = "nvarchar(MAX)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonManagedReference
    private BrandEntity brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private CategoryEntity category;

    @OneToMany(mappedBy ="product")
    @JsonManagedReference
    private List<OrderDetailEntity> orderDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    @JsonManagedReference
    private ImageEntity image;
}
