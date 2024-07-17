package com.example.demo.repositories;

import com.example.demo.entities.IdentityVerification;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class IdentityVerificationRepository extends GenericRepository {
    public IdentityVerificationRepository() {
    }

    public Long save(IdentityVerification identityVerification) {
        this.getEntityManager().persist(identityVerification);
        return identityVerification.getGkey();
    }

    public boolean update(IdentityVerification identityVerification) {
        IdentityVerification result = this.getEntityManager().merge(identityVerification);
        return true;
    }

    public IdentityVerification getIdentityVerification(Long gkey) {
        return this.getEntityManager().find(IdentityVerification.class, gkey);
    }

    public boolean delete(Long gkey) {
        Query query = this.getEntityManager().createQuery("delete from IdentityVerification where gkey = :gkey");
        query.setParameter("gkey", gkey);
        return query.executeUpdate() > 0;
    }
}
