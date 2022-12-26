package com.example.inventory_command_service;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryCommandServiceApplication.class, args);
    }
    @Bean
    public SimpleCommandBus axonServerCommandBus(){
        return SimpleCommandBus.builder().build();
    }
}
