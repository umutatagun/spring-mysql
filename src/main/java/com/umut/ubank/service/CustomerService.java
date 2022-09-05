package com.umut.ubank.service;

import com.umut.ubank.model.Account;
import com.umut.ubank.model.Address;
import com.umut.ubank.model.Customer;
import com.umut.ubank.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createCustomer(Customer customer);

    CustomerDto updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    CustomerDto addAddressToCustomer(Long id, Address address);

    CustomerDto addAccountToCustomer(Long id, Account account);

    List<Address> getCustomersAllAddresses(Long id);

    Integer getCustomersAllAmount(Long id);
}
