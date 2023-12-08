package com.code.orishop.controller.user;

import com.code.orishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("page","home.jsp");
        model.addAttribute("trandyProducts", productService.convert(productService.topTrend(8)));
        try{
            model.addAttribute("newProducts", productService.convert(productService.sortCreate().subList(0,8)));
        }catch (Exception e){
            model.addAttribute("newProducts", productService.convert(productService.sortCreate()));
        }
        return "/user/page";
    }
}
