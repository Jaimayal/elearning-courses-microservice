package com.jaimayal.coursesmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoursesMicroserviceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CoursesMicroserviceApplication.class, args);
    }
}
