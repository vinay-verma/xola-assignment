package com.xola.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.xola.assignment")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
