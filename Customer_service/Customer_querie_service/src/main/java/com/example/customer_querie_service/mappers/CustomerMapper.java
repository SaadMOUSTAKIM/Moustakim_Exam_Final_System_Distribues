package com.example.customer_querie_service.mappers;




import api.CustomerDTO;
import api.CustomerResponseDTO;
import api.EditCustomerDTO;
import com.example.customer_querie_service.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer fromCustomerDto(CustomerDTO customerDTO);
    CustomerResponseDTO fromCustomer(Customer customer);
    Customer fromCustomerEditDto(EditCustomerDTO customer);

}
