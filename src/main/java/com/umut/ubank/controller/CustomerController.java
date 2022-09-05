package com.umut.ubank.controller;

import com.umut.ubank.exception.NotFoundException;
import com.umut.ubank.model.Account;
import com.umut.ubank.model.Address;
import com.umut.ubank.model.Customer;
import com.umut.ubank.model.dto.CustomerDto;
import com.umut.ubank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity(this.customerService.getAllCustomers(),OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        return new ResponseEntity(this.customerService.getCustomerById(id),OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity(this.customerService.createCustomer(customer),CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return new ResponseEntity(this.customerService.updateCustomer(id, customer),CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return new ResponseEntity(OK);
    }

    @PostMapping("/create-address/{id}")
    public ResponseEntity<CustomerDto> addAddressToCustomer(@PathVariable Long id, @RequestBody Address address) {
        return new ResponseEntity(this.customerService.addAddressToCustomer(id, address),CREATED);
    }

    @PostMapping("/create-account/{id}")
    public ResponseEntity<CustomerDto> addAccountToCustomer(@PathVariable Long id, @RequestBody Account account) {
        return new ResponseEntity(this.customerService.addAccountToCustomer(id, account), CREATED);
    }

}
