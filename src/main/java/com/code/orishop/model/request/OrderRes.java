package com.code.orishop.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRes {
    private String customerName;
    private String phone;
    private String address;
    private Long orderBy =0L;
    private List<OrderDetailRes> orderDetalList  = new ArrayList<>();
}
