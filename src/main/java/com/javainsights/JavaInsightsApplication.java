package com.javainsights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JavaInsightsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaInsightsApplication.class, args);
    }
}
