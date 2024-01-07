package com.example.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> { } 
