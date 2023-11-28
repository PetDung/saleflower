package com.code.orishop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/403")
public class Error {
    @GetMapping("")
    public String index(){
        return "403";
    }
}
