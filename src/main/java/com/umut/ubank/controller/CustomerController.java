package com.umut.ubank.controller;

import com.umut.ubank.model.Customer;
import com.umut.ubank.model.dto.CustomerDto;
import com.umut.ubank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> returnH(){
        return new ResponseEntity("Hello Docker",OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity(this.customerService.getAllCustomers(),OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID id) {
        return new ResponseEntity(this.customerService.getCustomerById(id),OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity(this.customerService.createCustomer(customer),CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable UUID id, @RequestBody Customer customer) {
        return new ResponseEntity(this.customerService.updateCustomer(id, customer),CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        this.customerService.deleteCustomer(id);
        return new ResponseEntity(OK);
    }

}
