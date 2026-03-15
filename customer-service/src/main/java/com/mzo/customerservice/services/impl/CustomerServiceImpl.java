package com.mzo.customerservice.services.impl;

import com.mzo.customerservice.entities.Customer;
import com.mzo.customerservice.repositories.CustomerRepository;
import com.mzo.customerservice.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found "));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }
}
