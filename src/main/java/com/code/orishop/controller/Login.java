package com.code.orishop.controller;


import com.code.orishop.model.entity.UserEntity;
import com.code.orishop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class Login {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String login(@RequestParam(value = "error",defaultValue ="false") boolean error,
                        Model model){
        if(error){
            model.addAttribute("mess","Tài Khoản Hoặc Mật Khẩu Không Đúng!");
        }
        return "login";
    }

    @PostMapping("/login-handler")
    public String loginHandler(@RequestParam("email") String email,
                               @RequestParam("password") String password, HttpSession session){
        UserEntity user = userService.login(email, password);
        if(user != null){
            String url = (String) session.getAttribute("prevUrl");
            session.setAttribute("login",user);
            System.out.println(user.getRoles().get(0).getRoleName());
            if(url == null || url.contains("login")) url="/admin";
            return "redirect:/";
        }
        return "redirect:/login?error=true";
    }

}
