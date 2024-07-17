package com.example.demo.repositories;

import com.example.demo.entities.OperationType;
import com.example.demo.entities.OperationVerificationType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;

@Repository
public class OperationVerificationTypeRepository extends GenericRepository {
    public OperationVerificationTypeRepository() {
    }

    public Long save(OperationVerificationType operationVerificationType) {
        this.getEntityManager().persist(operationVerificationType);
        return operationVerificationType.getGkey();
    }

    public boolean update(OperationVerificationType operationVerificationType) {
        OperationVerificationType result = this.getEntityManager().merge(operationVerificationType);
        return true;
    }

    public List<OperationVerificationType> getAllOperationVerificationTypes() {
        TypedQuery<OperationVerificationType> query = this.getEntityManager().createQuery("select ovt from OperationVerificationType ovt", OperationVerificationType.class);
        return query.getResultList();
    }

    public List<OperationVerificationType> getOperationVerificationTypes(OperationType operationType) {
        Query query = this.getEntityManager().createQuery("select from OperationVerificationType where ");

        return Collections.emptyList();
    }

    public boolean delete(Long gkey) {
        Query query = this.getEntityManager().createQuery("delete from OperationVerificationType where gkey = :gkey");
        query.setParameter("gkey", gkey);
        return query.executeUpdate() > 0;
    }
}
