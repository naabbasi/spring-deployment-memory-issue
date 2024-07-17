package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepository extends GenericRepository {

    public List<Customer> findAll() {
        return this.getEntityManager().createQuery("from Customer", Customer.class).getResultList();
    }

    public Customer findById(Long customerId) {
        try {
            TypedQuery<Customer> customerTypedQuery = this.getEntityManager().createQuery("from Customer where gkey = :gkey ", Customer.class);
            customerTypedQuery.setParameter("gkey", customerId);
            return customerTypedQuery.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }
}
