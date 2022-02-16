package com.codecool.dadsinventory;

import com.codecool.dadsinventory.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class DadsInventoryApplication {


    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(DadsInventoryApplication.class, args);
    }


    @Bean
    CommandLineRunner run(InitService initService){
        return args -> {
            initService.seedDatabase();
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
