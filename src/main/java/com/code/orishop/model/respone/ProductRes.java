package com.code.orishop.model.respone;

import com.code.orishop.model.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRes {
    private Long id;
    private String name;
    private double price;
    private int quantityInStock;
    private Long brandId;
    private String brandName;
    private Long categoryId;
    private String categoryName;
    private String image;
    private Date updatedAt;
    private Date createdAt;

    public ProductRes(ProductEntity p) {
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.quantityInStock = p.getQuantityInStock();
        this.brandId = p.getBrand().getId();
        this.brandName =p.getBrand().getName();
        this.categoryId = p.getCategory().getId();
        this.categoryName = p.getCategory().getName();
        if( p.getImage() != null ){
            this.image = "/api/v1/image/file/product/"+ p.getImage().getId();
        }else {
            this.image ="/img/nothing.png";
        }
        this.updatedAt = p.getUpdatedAt();
        this.createdAt = p.getCreatedAt();
    }
}
