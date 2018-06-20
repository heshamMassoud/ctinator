package com.heshammassoud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Nonnull;

@SpringBootApplication
public class Application {
    public static void main(@Nonnull final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
