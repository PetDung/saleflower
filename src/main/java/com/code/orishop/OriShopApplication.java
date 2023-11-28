package com.code.orishop;

import com.code.orishop.service.Boot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OriShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OriShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeData(Boot boot) {
        return args -> {
            boot.boot();
        };
    }

}
