package com.alex.elearning.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.alex.elearning.entities.vod"})
@ComponentScan(basePackages = {
        "com.alex.elearning.swagger",
        "com.alex.elearning.vod",
        "com.alex.elearning.exception"})
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }
}
