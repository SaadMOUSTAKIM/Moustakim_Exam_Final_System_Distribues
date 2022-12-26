package com.example.customer_command_service.controllers;


import api.CreateNewCustomerCommand;
import api.CustomerDTO;
import api.EditCustomerCommand;
import api.EditCustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/commands")
@CrossOrigin("*")
public class CustomerCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    public CustomerCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }
    @PostMapping("/customer/create")
    public CompletableFuture<String> addNewCustomerCommand(@RequestBody CustomerDTO request){
        return this.commandGateway.send(new CreateNewCustomerCommand(
                UUID.randomUUID().toString(),
                request
        ));
    }
    @GetMapping("/eventStore/{id}")
    public Stream streamEvents(@PathVariable String id){
        return eventStore.readEvents(id).asStream();
    }


    @PutMapping("/customer/edit/{id}")
    public CompletableFuture<String> editCustomer(@RequestBody EditCustomerDTO request, @RequestParam String id){
        return this.commandGateway.send(new EditCustomerCommand(
                id,
                request
        ));
    }
}
