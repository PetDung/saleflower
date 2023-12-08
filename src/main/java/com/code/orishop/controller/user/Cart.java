package com.code.orishop.controller.user;

import com.code.orishop.model.entity.UserEntity;
import com.code.orishop.service.CartService;
import com.code.orishop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/user")
public class Cart {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @GetMapping("/add-cart")
    public String addCart(@RequestParam("quantity") Integer quantity,
                                     @RequestParam("id") Long id, HttpSession session)
    {
        try {
            UserEntity user = userService.getLogin(session);
            cartService.add(quantity,id,user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/view-cart")
    public String viewCart(HttpSession session, Model model)
    {
        try {
            UserEntity user = userService.getLogin(session);
            model.addAttribute("page","cart.jsp");
            model.addAttribute("cart",cartService.getCartByIdUser(user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/user/page";
    }

    private String getCurrentUrl() {
        // Lấy đường dẫn hiện tại từ RequestContext
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getRequestURI() + "?" + request.getQueryString();
    }
}
