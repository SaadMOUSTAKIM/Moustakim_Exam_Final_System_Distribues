package com.example.customer_querie_service.repositories;

import com.example.customer_querie_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
