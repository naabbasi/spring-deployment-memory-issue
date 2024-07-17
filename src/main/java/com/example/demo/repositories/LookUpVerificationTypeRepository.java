package com.example.demo.repositories;

import com.example.demo.entities.LookUpVerificationType;
import com.example.demo.entities.VerificationType;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class LookUpVerificationTypeRepository extends GenericRepository {
    public LookUpVerificationTypeRepository() {
    }

    public VerificationType save(LookUpVerificationType lookUplookUpVerificationType) {
        this.getEntityManager().persist(lookUplookUpVerificationType);
        return lookUplookUpVerificationType.getAbsherCode();
    }

    public List<LookUpVerificationType> getLooUpVerificationTypes() {
        TypedQuery<LookUpVerificationType> query = this.getEntityManager().createQuery("select lvt from LookUpVerificationType lvt", LookUpVerificationType.class);
        return query.getResultList();
    }

    public LookUpVerificationType getVerificationType(VerificationType verificationType) {
        TypedQuery<LookUpVerificationType> query = this.getEntityManager().createQuery("select lvt from LookUpVerificationType lvt where lvt.absherCode = :verificationType", LookUpVerificationType.class);
        query.setParameter("verificationType", verificationType);
        return query.getSingleResult();
    }

    public boolean delete(VerificationType verificationType) {
        Query query = this.getEntityManager().createQuery("delete from LookUpVerificationType lvt where lvt.absherCode = :verificationType");
        query.setParameter("verificationType", verificationType);
        return query.executeUpdate() > 0;
    }
}
