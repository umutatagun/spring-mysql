package com.umut.ubank.process;

import com.umut.ubank.model.Customer;
import com.umut.ubank.service.CustomerService;
import com.umut.ubank.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManagerProcess {
    private final ManagerService managerService;
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(ManagerProcess.class);

    public ManagerProcess(ManagerService managerService, CustomerService customerService) {
        this.managerService = managerService;
        this.customerService = customerService;
    }

    public void shuffleCustomers(List<Customer> customers) {
        List<Customer> jr = customers.stream()
                .filter(customer -> customerService.getCustomersAllAmount(customer.getId()) < 10000)
                .collect(Collectors.toList());

        List<Customer> sr = customers.stream()
                .filter(customer ->
                        customerService.getCustomersAllAmount(customer.getId()) < 20000 &&
                        customerService.getCustomersAllAmount(customer.getId()) >= 10000
                )
                .collect(Collectors.toList());

        List<Customer> expert = customers.stream()
                .filter(customer ->
                        customerService.getCustomersAllAmount(customer.getId()) >= 20000 &&
                        customerService.getCustomersAllAmount(customer.getId()) < 30000)
                .collect(Collectors.toList());

        List<Customer> director = customers.stream()
                .filter(customer ->
                        customerService.getCustomersAllAmount(customer.getId()) >= 30000 &&
                        customerService.getCustomersAllAmount(customer.getId()) < 40000)
                .collect(Collectors.toList());

        List<Customer> c = customers.stream()
                .filter(customer ->
                        customerService.getCustomersAllAmount(customer.getId()) >=40000)
                .collect(Collectors.toList());


    }
}
