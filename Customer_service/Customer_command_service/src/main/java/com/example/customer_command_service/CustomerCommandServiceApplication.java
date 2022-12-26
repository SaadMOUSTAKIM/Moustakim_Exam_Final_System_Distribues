package com.example.customer_command_service;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCommandServiceApplication.class, args);
    }
    @Bean
    public SimpleCommandBus axonServerCommandBus(){
        return SimpleCommandBus.builder().build();
    }

}
