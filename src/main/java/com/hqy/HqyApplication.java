package com.hqy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.hqy")
public class HqyApplication {
    public static void main(String[] args) {
        SpringApplication.run(HqyApplication.class, args);
    }
}
