package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.LookUpOperation;
import com.example.demo.entities.OperationType;

import javax.persistence.TypedQuery;

@Repository
public class LookUpOperationTypeRepository extends GenericRepository {
    public LookUpOperationTypeRepository() {
    }

    public OperationType save(LookUpOperation lookUpOperationType) {
        this.getEntityManager().persist(lookUpOperationType);
        return lookUpOperationType.getAbsherCode();
    }

    public LookUpOperation getOperationType(OperationType operationType) {
        TypedQuery<LookUpOperation> query = this.getEntityManager().createQuery("select lo from LookUpOperation lo where lo.absherCode = :operationType", LookUpOperation.class);
        query.setParameter("operationType", operationType);
        return query.getSingleResult();
    }
}
