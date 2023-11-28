package com.code.orishop.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

    @Bean
    public FilterRegistrationBean<MyCustomFilter> myCustomFilter() {
        FilterRegistrationBean<MyCustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyCustomFilter());
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }
}
