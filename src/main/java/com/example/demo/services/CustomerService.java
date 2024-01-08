package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.utils.LogUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final LogUtils logUtils;

    public CustomerService(CustomerRepository customerRepository, LogUtils logUtils) {
        this.customerRepository = customerRepository;
        this.logUtils = logUtils;
    }

    public Iterable<Customer> all() {
        this.logUtils.log("Fetching all customers");
        return this.customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        this.logUtils.log("Fetching customer by id: {}", customerId);
        return this.customerRepository.findById(customerId);
    }
}
