package com.antonio32a.privateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PrivateAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrivateAPIApplication.class, args);
    }
}
