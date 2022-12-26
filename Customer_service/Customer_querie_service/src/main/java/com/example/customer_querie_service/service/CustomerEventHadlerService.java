package com.example.customer_querie_service.service;

import api.CustomerCreatedEvent;
import api.CustomerEditedEvent;
import api.DataResponseDTO;
import api.SubscribeToEventsQuery;
import com.example.customer_querie_service.entities.Customer;
import com.example.customer_querie_service.mappers.CustomerMapper;
import com.example.customer_querie_service.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class CustomerEventHadlerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMappers;
    private QueryUpdateEmitter queryUpdateEmitter;


    public CustomerEventHadlerService(CustomerRepository customerRepository,  CustomerMapper customerMappers, QueryUpdateEmitter queryUpdateEmitter) {
        this.customerRepository = customerRepository;
        this.customerMappers = customerMappers;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event, EventMessage<CustomerCreatedEvent> eventMessage){
        log.info("*************** Query Event handler **************");
        log.info("CustomerCreatedEvent occured");
        Customer customer=customerMappers.fromCustomerDto(event.getPayload());
        customer.setCustomerId(event.getId());
        customerRepository.save(customer);
        DataResponseDTO eventDataResponseDTO=new DataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);

    }

    @EventHandler
    public void on(CustomerEditedEvent event){
        log.info("*************** Query Event handler **************");
        log.info("CustomerEditedEvent occured");
        Customer customer=customerRepository.findById(event.getId())
                .orElseThrow(()->new RuntimeException("Customer not found"));
        customer=customerMappers.fromCustomerEditDto(event.getPayload());
        customerRepository.save(customer);
        DataResponseDTO eventDataResponseDTO=new DataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);
    }


}
