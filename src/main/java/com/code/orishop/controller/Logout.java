package com.code.orishop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class Logout {
    @GetMapping("")
    public String index(HttpSession session){
        session.removeAttribute("login");
        return "redirect:/login";
    }
}
