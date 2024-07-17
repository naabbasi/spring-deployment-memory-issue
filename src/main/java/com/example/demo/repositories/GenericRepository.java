package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GenericRepository {
    @PersistenceContext(unitName = "sysPersistentUnit")
    private EntityManager entityManager;

    EntityManager getEntityManager() {
        return this.entityManager;
    }
}
