package com.code.orishop.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReq {

    private String userName;
    private String password;
    private String email;
    private String phone;
    private Long role;
}
