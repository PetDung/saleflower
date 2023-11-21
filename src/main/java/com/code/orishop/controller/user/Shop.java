package com.code.orishop.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/shop")
public class Shop {

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("page","shop.jsp");
        return "/user/page";
    }
}
