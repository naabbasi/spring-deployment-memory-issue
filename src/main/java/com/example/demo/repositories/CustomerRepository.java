package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    @PersistenceContext(unitName = "sysPersistentUnit")
    private final EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Customer> findAll() {
        return this.entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    public Customer findById(Long customerId) {
        TypedQuery<Customer> customerTypedQuery = this.entityManager.createQuery("from Customer where gkey = :gkey ", Customer.class);
        customerTypedQuery.setParameter("gkey", customerId);
        return customerTypedQuery.getSingleResult();
    }
}
