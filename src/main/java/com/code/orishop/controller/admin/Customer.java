package com.code.orishop.controller.admin;

import com.code.orishop.service.RoleService;
import com.code.orishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/users")
public class Customer {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public String getCustomers(Model model) {
        model.addAttribute("title", "Admin User");
        model.addAttribute("page", "customer.jsp");
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("users",userService.getAll());
        return "/admin/index";
    }
}
