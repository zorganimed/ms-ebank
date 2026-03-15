package com.mzo.customerservice.services;

import com.mzo.customerservice.entities.Customer;

import java.util.List;

public interface CustomerService {

    public Customer findCustomerById(Long id);
    public List<Customer> findAllCustomers();
    public Customer saveCustomer(Customer customer);
}
