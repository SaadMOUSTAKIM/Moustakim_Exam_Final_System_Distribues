package com.example.customer_querie_service.service;



import api.*;
import com.example.customer_querie_service.entities.Customer;
import com.example.customer_querie_service.mappers.CustomerMapper;
import com.example.customer_querie_service.repositories.CustomerRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerQueryHandler {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMappers;


    public CustomerQueryHandler(CustomerRepository customerRepository, CustomerMapper customerMappers) {
        this.customerRepository = customerRepository;
        this.customerMappers = customerMappers;
    }

    @QueryHandler
    public List<CustomerResponseDTO>  handler(GetAllCustomersQuery query){
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers.stream().map(customer->customerMappers.fromCustomer(customer))
                .collect(Collectors.toList());
    }

    @QueryHandler
    public CustomerResponseDTO  handler(GetCustomerById query){
        Customer customer=customerRepository.findById(query.getCustomerId())
                .orElseThrow(()->new RuntimeException("Customer Not found"));
        CustomerResponseDTO customerResponseDTO=customerMappers.fromCustomer(customer);

        return customerResponseDTO;
    }
    @QueryHandler
    public List<DataResponseDTO> handle(SubscribeToEventsQuery query) {
        return new ArrayList<>();
    }

}
