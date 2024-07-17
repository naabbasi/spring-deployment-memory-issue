package com.example.demo.repositories;

import com.example.demo.entities.OperationType;
import com.example.demo.entities.VerificationTypeRule;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;

@Repository
public class VerificationTypeRuleRepository extends GenericRepository {
    public VerificationTypeRuleRepository() {
    }

    public Long save(VerificationTypeRule verificationTypeRule) {
        this.getEntityManager().persist(verificationTypeRule);
        return verificationTypeRule.getGkey();
    }

    public boolean update(VerificationTypeRule verificationTypeRule) {
        VerificationTypeRule result = this.getEntityManager().merge(verificationTypeRule);
        return true;
    }

    public List<VerificationTypeRule> getOperationVerificationTypes(OperationType operationType) {
        Query query = this.getEntityManager().createQuery("select vtr from VerificationTypeRule vtr");

        return Collections.emptyList();
    }

    public boolean delete(Long gkey) {
        Query query = this.getEntityManager().createQuery("delete from VerificationTypeRule where gkey = :gkey");
        query.setParameter("gkey", gkey);
        return query.executeUpdate() > 0;
    }

    public List<VerificationTypeRule> getAllVerificationTypeRules() {
        TypedQuery<VerificationTypeRule> query = this.getEntityManager().createQuery("select vtr from VerificationTypeRule vtr", VerificationTypeRule.class);
        return query.getResultList();
    }
}
