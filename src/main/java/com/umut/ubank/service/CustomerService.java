package com.umut.ubank.service;

import com.umut.ubank.model.Customer;
import com.umut.ubank.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(UUID id);

    CustomerDto createCustomer(Customer customer);

    CustomerDto updateCustomer(UUID id, Customer customer);

    void deleteCustomer(UUID id);
}
