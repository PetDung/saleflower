package com.code.orishop.model.request;

import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailRes {
    private  Long productId;
    public Integer quantity;
}
