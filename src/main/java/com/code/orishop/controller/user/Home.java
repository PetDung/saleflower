package com.code.orishop.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("page","home.jsp");
        return "/user/page";
    }
}
