package com.code.orishop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity extends BaseEntity{
    @Column(name = "name",columnDefinition = "nvarchar(MAX)")
    private String name;

    @OneToMany(mappedBy ="category",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ProductEntity> productList;
}
