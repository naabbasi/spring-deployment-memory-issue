package com.example.demo.services;

import com.example.demo.entities.LookUpOperation;
import com.example.demo.entities.OperationType;
import com.example.demo.repositories.LookUpOperationTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LookUpOperationTypeService {
    private final LookUpOperationTypeRepository lookUpOperationTypeRepository;

    public LookUpOperationTypeService(LookUpOperationTypeRepository lookUpOperationTypeRepository) {
        this.lookUpOperationTypeRepository = lookUpOperationTypeRepository;
    }

    @Transactional
    public OperationType save(LookUpOperation lookUpOperationType) {
        return this.lookUpOperationTypeRepository.save(lookUpOperationType);
    }

    public LookUpOperation getOperationType(OperationType operationType) {
        return this.lookUpOperationTypeRepository.getOperationType(operationType);
    }
}
