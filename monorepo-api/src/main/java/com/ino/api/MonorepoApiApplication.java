package com.ino.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ino.common.*", "com.ino.api.*"})
public class MonorepoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonorepoApiApplication.class, args);
    }
}
