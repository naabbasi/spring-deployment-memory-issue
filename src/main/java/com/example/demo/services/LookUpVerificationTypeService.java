package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.LookUpVerificationType;
import com.example.demo.entities.VerificationType;
import com.example.demo.repositories.LookUpVerificationTypeRepository;

@Service
public class LookUpVerificationTypeService {
    private final LookUpVerificationTypeRepository lookUpVerificationTypeRepository;

    public LookUpVerificationTypeService(LookUpVerificationTypeRepository lookUpVerificationTypeRepository) {
        this.lookUpVerificationTypeRepository = lookUpVerificationTypeRepository;
    }

    @Transactional
    public VerificationType save(LookUpVerificationType lookUplookUpVerificationType) {
        return this.lookUpVerificationTypeRepository.save(lookUplookUpVerificationType);
    }

    public LookUpVerificationType getVerificationType(VerificationType verificationType) {
        return this.lookUpVerificationTypeRepository.getVerificationType(verificationType);
    }

    public List<LookUpVerificationType> getAllVerificationType() {
        return this.lookUpVerificationTypeRepository.getLooUpVerificationTypes();
    }

    @Transactional
    public Boolean deleteAll() {
        for(LookUpVerificationType lookUpVerificationType : this.getAllVerificationType()) {
            this.lookUpVerificationTypeRepository.delete(lookUpVerificationType.getAbsherCode());
        }

        return true;
    }
}
