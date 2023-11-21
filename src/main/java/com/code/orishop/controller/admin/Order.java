package com.code.orishop.controller.admin;

import com.code.orishop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class Order {
    @Autowired
    OrderService orderService;

    @GetMapping("")
    public String getOrders(Model model) {
        model.addAttribute("title", "Admin Order");
        model.addAttribute("page", "order.jsp");
        model.addAttribute("orders", orderService.getAll());
        return "/admin/index";
    }
    @GetMapping("/detail/{id}")
    public String getOrderDetail(@PathVariable("id") Long id,Model model) {
        model.addAttribute("title", "Admin Order Detail");
        model.addAttribute("page", "order-detail.jsp");
        model.addAttribute("o", orderService.getById(id));
        return "/admin/index";
    }
}
