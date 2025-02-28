package com.example.msasbnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MsaSbNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaSbNewsApplication.class, args);
    }

}
