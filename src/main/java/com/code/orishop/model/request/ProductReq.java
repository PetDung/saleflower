package com.code.orishop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductReq {
    private String name;
    private double price;
    private Integer quantityInStock;
    private Long category;
    private Long brand;
    private MultipartFile image = null;
}
