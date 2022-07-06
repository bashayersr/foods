package com.example.FoodTrucksSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@SpringBootApplication
//@ComponentScan({"com.example.FoodTrucksSystem.Controller", "com.example.FoodTrucksSystem.Service"})
//@EntityScan("com.example.FoodTrucksSystem.model")
//@EnableJpaRepositories("com.example.FoodTrucksSystem.Repository")
@SpringBootApplication
public class FoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodApplication.class, args);
    }

}
