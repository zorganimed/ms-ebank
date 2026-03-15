package com.mzo.customerservice.web;

import com.mzo.customerservice.entities.Customer;
import com.mzo.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable Long id){
        return service.findCustomerById(id);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        return service.findAllCustomers();
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        return service.saveCustomer(customer);
    }
}
