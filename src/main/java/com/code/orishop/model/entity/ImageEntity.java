package com.code.orishop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`image`")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImageEntity extends BaseEntity{
    @Lob
    @Column(name = "data", columnDefinition = "VARBINARY(MAX)")
    private byte[] data;

    @Column(name = "type")
    private String type; // image/png, image/jpg, ...

    @OneToOne(mappedBy = "image")
    @JsonBackReference
    private ProductEntity product;
}
