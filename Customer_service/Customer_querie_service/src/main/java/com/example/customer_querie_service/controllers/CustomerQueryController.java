package com.example.customer_querie_service.controllers;

import api.CustomerResponseDTO;
import api.GetAllCustomersQuery;
import api.GetCustomerById;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequestMapping("/query")
@CrossOrigin("*")
public class CustomerQueryController {
    private QueryGateway queryGateway;

    public CustomerQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/customers/all")
    public CompletableFuture<List<CustomerResponseDTO>> allCustomers(){
        return queryGateway.query(
                new GetAllCustomersQuery()
                , ResponseTypes.multipleInstancesOf(CustomerResponseDTO.class)
        );
    }

    @GetMapping("/customer/byId/{id}")
    public CompletableFuture<CustomerResponseDTO> geCustomerById(@PathVariable String id){
        return queryGateway.query(
                new GetCustomerById(id)
                , ResponseTypes.instanceOf(CustomerResponseDTO.class)
        );
    }

}
