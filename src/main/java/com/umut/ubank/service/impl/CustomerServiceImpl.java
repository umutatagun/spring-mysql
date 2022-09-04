package com.umut.ubank.service.impl;

import com.umut.ubank.model.Customer;
import com.umut.ubank.model.dto.CustomerDto;
import com.umut.ubank.repository.CustomerRepository;
import com.umut.ubank.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> customerToCustomerDto(customer))
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(UUID id) {
        return customerToCustomerDto(findById(id));
    }

    public CustomerDto createCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerToCustomerDto(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(UUID id, Customer customer) {
        Customer c1 = findById(id);
        c1.setAccounts(customer.getAccounts());
        c1.setAddresses(customer.getAddresses());
        c1.setEmail(customer.getEmail());
        c1.setGender(customer.getGender());
        c1.setName(customer.getName());
        c1.setSurname(customer.getSurname());
        c1.setDateOfBirth(customer.getDateOfBirth());
        c1.setUsername(customer.getUsername());
        c1.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(c1);

        return customerToCustomerDto(c1);
    }

    public void deleteCustomer(UUID id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }

    public CustomerDto customerToCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer findById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id "+id));
    }

}
