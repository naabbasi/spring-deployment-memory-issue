package com.example.demo.services;

import com.example.demo.entities.LookUpVerificationType;
import com.example.demo.entities.OperationType;
import com.example.demo.entities.OperationVerificationType;
import com.example.demo.repositories.OperationVerificationTypeRepository;
import com.example.demo.utils.LogUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationVerificationTypeService {
    private final LogUtils logUtils;
    private final OperationVerificationTypeRepository operationVerificationTypeRepository;

    public OperationVerificationTypeService(LogUtils logUtils, OperationVerificationTypeRepository operationVerificationTypeRepository) {
        this.logUtils = logUtils;
        this.operationVerificationTypeRepository = operationVerificationTypeRepository;
    }

    @Transactional
    public Long saveOperationVerificationType(OperationVerificationType operationVerificationType) {
        this.logUtils.log("Saving Operation verification type");
        this.operationVerificationTypeRepository.save(operationVerificationType);
        return operationVerificationType.getGkey();
    }

    @Transactional
    public boolean updateMobileNumber(OperationVerificationType operationVerificationType) {
        this.logUtils.log("Update identity verification");
        return this.operationVerificationTypeRepository.update(operationVerificationType);
    }

    public List<OperationVerificationType> getAllOperationVerificationTypes() {
        this.logUtils.log("Get all operation verification types");
        return this.operationVerificationTypeRepository.getAllOperationVerificationTypes();
    }

    public List<OperationVerificationType> getOperationVerificationTypes(OperationType operationType) {
        this.logUtils.log("Get operation verification type by Id: {}", operationType);
        return this.operationVerificationTypeRepository.getOperationVerificationTypes(operationType);
    }

    @Transactional
    public boolean delete(Long gkey) {
        this.logUtils.log("Delete identity verification: {}", gkey);
        return this.operationVerificationTypeRepository.delete(gkey);
    }

    public List<LookUpVerificationType> getVerificationType(OperationType operationType) {
        return null;
    }

    @Transactional
    public Boolean deleteAll() {
        this.logUtils.log("Deleting all operation verification types");
        for(OperationVerificationType operationVerificationType : this.operationVerificationTypeRepository.getAllOperationVerificationTypes()) {
            this.operationVerificationTypeRepository.delete(operationVerificationType.getGkey());
        }
        
        return true;
    }
}
