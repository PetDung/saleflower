package com.code.orishop.config;

import com.code.orishop.model.entity.UserEntity;
import com.code.orishop.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class MyCustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        UserEntity userLogin =(UserEntity) session.getAttribute("login");

        String queryString = httpRequest.getQueryString();
        String requestUrl = httpRequest.getRequestURL().toString();
        session.setAttribute("prevUrl", requestUrl);
        if(queryString != null) requestUrl +=queryString;

        if (userLogin != null) {
            String role= userLogin.getRoles().get(0).getRoleName();
            if(role.equalsIgnoreCase("CUSTOMER")) httpResponse.sendRedirect("/403");
            chain.doFilter(request, response);
            return;
        }

        httpResponse.sendRedirect("/login");
        chain.doFilter(request, response);
    }

}
