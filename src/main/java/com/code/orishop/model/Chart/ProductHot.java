package com.code.orishop.model.Chart;

import com.code.orishop.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductHot {
    private ProductEntity product;
    private Long totalQuantity;
}
