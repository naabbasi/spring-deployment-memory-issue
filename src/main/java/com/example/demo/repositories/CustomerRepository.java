package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepository {
    @PersistenceContext(unitName = "sysPersistentUnit")
    private EntityManager ENTITY_MANAGER;


    public List<Customer> findAll() {
        return this.ENTITY_MANAGER.createQuery("from Customer", Customer.class).getResultList();
    }

    public Customer findById(Long customerId) {
        Query query = this.ENTITY_MANAGER.createQuery("from Customer where id = :customerId", Customer.class);
        query.setParameter("customerId", customerId);
        return (Customer) query.getSingleResult();
    }
}
