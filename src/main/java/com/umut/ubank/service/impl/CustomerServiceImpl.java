package com.umut.ubank.service.impl;

import com.umut.ubank.exception.EntityAlreadyExistException;
import com.umut.ubank.exception.NotFoundException;
import com.umut.ubank.model.Account;
import com.umut.ubank.model.Address;
import com.umut.ubank.model.Customer;
import com.umut.ubank.model.dto.CustomerDto;
import com.umut.ubank.repository.CustomerRepository;
import com.umut.ubank.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(
            ModelMapper modelMapper,
            CustomerRepository customerRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> customer.getIsActive() == Boolean.TRUE)
                .map(this::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = findById(id);
        if(customer.getIsActive() == Boolean.FALSE) {
           throw new NotFoundException("Customer is not active!");
        }
        return customerToCustomerDto(customer);
    }

    public CustomerDto createCustomer(Customer customer) {
        if(customerRepository.findById(customer.getId()).isPresent()) {
            throw new EntityAlreadyExistException("Customer already exist with id "+customer.getId());
        }
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        return customerToCustomerDto(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(Long id, Customer customer) {
        Customer c1 = findById(id);
        c1.setAccounts(customer.getAccounts());
        c1.setAddresses(customer.getAddresses());
        c1.setEmail(customer.getEmail());
        c1.setGender(customer.getGender());
        c1.setName(customer.getName());
        c1.setSurname(customer.getSurname());
        c1.setDateOfBirth(customer.getDateOfBirth());
        c1.setUsername(customer.getUsername());
        c1.setIsActive(customer.getIsActive());
        c1.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        c1.setLastModifiedDate(new Date());
        c1.setLastModifiedBy("Admin");
        customerRepository.save(c1);

        return customerToCustomerDto(c1);
    }

    public void deleteCustomer(Long id) {
        Customer customer = findById(id);
        customer.setIsActive(Boolean.FALSE);
        customer.getAddresses().forEach(address -> address.setIsActive(Boolean.FALSE));
        customer.getAccounts().forEach(account -> account.setIsActive(Boolean.FALSE));

        customerRepository.save(customer);
    }

    public CustomerDto addAddressToCustomer(Long id, Address address) {
        Customer customer = findById(id);
        customer.addAddress(address);

        return customerToCustomerDto(customerRepository.save(customer));
    }

    public CustomerDto addAccountToCustomer(Long id, Account account) {
        Customer customer = findById(id);
        customer.addAccount(account);

        return customerToCustomerDto(customerRepository.save(customer));
    }

    public CustomerDto customerToCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id "+id));
    }

}
