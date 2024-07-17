package com.example.demo.services;

import com.example.demo.entities.OperationType;
import com.example.demo.entities.VerificationTypeRule;
import com.example.demo.repositories.VerificationTypeRuleRepository;
import com.example.demo.utils.LogUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VerificationTypeRuleService {
    private final LogUtils logUtils;
    private final VerificationTypeRuleRepository verificationTypeRuleRepository;

    public VerificationTypeRuleService(LogUtils logUtils, VerificationTypeRuleRepository verificationTypeRuleRepository) {
        this.logUtils = logUtils;
        this.verificationTypeRuleRepository = verificationTypeRuleRepository;
    }

    @Transactional
    public Long saveVerificationTypeRule(VerificationTypeRule verificationTypeRule) {
        this.logUtils.log("Saving verification type rule: {}", verificationTypeRule);
        return this.verificationTypeRuleRepository.save(verificationTypeRule);
    }

    @Transactional
    public boolean updateVerificationTypeRule(VerificationTypeRule verificationTypeRule) {
        this.logUtils.log("Updating verification type rule: {}", verificationTypeRule);
        return this.verificationTypeRuleRepository.update(verificationTypeRule);
    }

    public List<VerificationTypeRule> getVerificationTypeRules(OperationType operationType) {
        this.logUtils.log("Fetching all verification type rule: {}", operationType);
        return this.verificationTypeRuleRepository.getOperationVerificationTypes(operationType);
    }

    @Transactional
    public boolean deleteAll() {
        this.logUtils.log("Deleting all verification type rules");
        for(VerificationTypeRule verificationTypeRule : this.verificationTypeRuleRepository.getAllVerificationTypeRules()) {
            this.verificationTypeRuleRepository.delete(verificationTypeRule.getGkey());
        }
        return true;
    }
}
