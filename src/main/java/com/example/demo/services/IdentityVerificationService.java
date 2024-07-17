package com.example.demo.services;

import com.example.demo.entities.IdentityVerification;
import com.example.demo.entities.LookUpVerificationType;
import com.example.demo.entities.OperationType;
import com.example.demo.repositories.IdentityVerificationRepository;
import com.example.demo.utils.LogUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IdentityVerificationService {
    private final LogUtils logUtils;
    private final IdentityVerificationRepository identityVerificationRepository;

    public IdentityVerificationService(LogUtils logUtils, IdentityVerificationRepository identityVerificationRepository) {
        this.logUtils = logUtils;
        this.identityVerificationRepository = identityVerificationRepository;
    }

    @Transactional
    public Long verifyIdentity(IdentityVerification identityVerification) {
        this.logUtils.log("Saving identity verification");
        //TODO: Check Id valid
        identityVerification.setValid(true);
        this.identityVerificationRepository.save(identityVerification);
        return identityVerification.getGkey();
    }

    @Transactional
    public boolean updateMobileNumber(IdentityVerification identityVerification) {
        this.logUtils.log("Update identity verification");
        return this.identityVerificationRepository.update(identityVerification);
    }

    public IdentityVerification getVerifiedUser(Long gkey) {
        this.logUtils.log("Get identity verification by Id: {}", gkey);
        return this.identityVerificationRepository.getIdentityVerification(gkey);
    }

    @Transactional
    public boolean delete(Long gkey) {
        this.logUtils.log("Delete identity verification: {}", gkey);
        return this.identityVerificationRepository.delete(gkey);
    }

    public List<LookUpVerificationType> getVerificationType(OperationType operationType) {
        return null;
    }
}
