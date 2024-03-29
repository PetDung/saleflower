package com.code.orishop.controller.admin;

import com.code.orishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Admin {
    @Autowired
    ProductService productService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("page","home.jsp");
        model.addAttribute("top5",productService.top(5));
        return "/admin/index";
    }
}
