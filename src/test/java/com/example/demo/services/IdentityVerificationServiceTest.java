package com.example.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entities.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IdentityVerificationServiceTest {
    @Autowired
    private IdentityVerificationService identityVerificationService;
    @Autowired
    private LookUpOperationTypeService lookUpOperationTypeService;
    @Autowired
    private LookUpVerificationTypeService lookUpVerificationTypeService;
    @Autowired
    private OperationVerificationTypeService operationVerificationTypeService;
    @Autowired
    private VerificationTypeRuleService verificationTypeRuleService;
    private static final OperationType []lastSavedOperationId = new OperationType[3];
    private static final VerificationType[]lastSavedVerificationType = new VerificationType[2];
    private static Long lastSavedId;

    @Test
    @Order(0)
    void setUp() {
        LookUpOperation lookUpOperationType = new LookUpOperation();
        lookUpOperationType.setAbsherCode(OperationType.UPDATE_MOBILE_NUMBER);
        lookUpOperationType.setAbsherLabelEn("UPDATE_MOBILE_NUMBER");
        lookUpOperationType.setAbsherLabelAr("UPDATE_MOBILE_NUMBER");
        lookUpOperationType.setCreatedBy("system");
        lastSavedOperationId[0] = this.lookUpOperationTypeService.save(lookUpOperationType);

        lookUpOperationType = new LookUpOperation();
        lookUpOperationType.setAbsherCode(OperationType.ACTIVATE_ID);
        lookUpOperationType.setAbsherLabelEn("ACTIVATE_ID");
        lookUpOperationType.setAbsherLabelAr("ACTIVATE_ID");
        lookUpOperationType.setCreatedBy("system");
        lastSavedOperationId[1] = this.lookUpOperationTypeService.save(lookUpOperationType);

        lookUpOperationType = new LookUpOperation();
        lookUpOperationType.setAbsherCode(OperationType.DEACTIVATE_ID);
        lookUpOperationType.setAbsherLabelEn("DEACTIVATE_ID");
        lookUpOperationType.setAbsherLabelAr("DEACTIVATE_ID");
        lookUpOperationType.setCreatedBy("system");
        lastSavedOperationId[2] = this.lookUpOperationTypeService.save(lookUpOperationType);

        LookUpVerificationType lookUpVerificationType = new LookUpVerificationType();
        lookUpVerificationType.setAbsherCode(VerificationType.FINGER_PRINTS);
        lookUpVerificationType.setAbsherLabelEn("FINGER_PRINTS");
        lookUpVerificationType.setAbsherLabelAr("FINGER_PRINTS");
        lookUpVerificationType.setCreatedBy("system");
        lastSavedVerificationType[0] = this.lookUpVerificationTypeService.save(lookUpVerificationType);

        lookUpVerificationType = new LookUpVerificationType();
        lookUpVerificationType.setAbsherCode(VerificationType.FACE);
        lookUpVerificationType.setAbsherLabelEn("FACE");
        lookUpVerificationType.setAbsherLabelAr("FACE");
        lookUpVerificationType.setCreatedBy("system");
        lastSavedVerificationType[1] = this.lookUpVerificationTypeService.save(lookUpVerificationType);
    }

    @Test
    @Order(10)
    void verifyIdentity() {
        IdentityVerification identityVerification = new IdentityVerification();
        identityVerification.setUserId("2545149516");
        identityVerification.setExpiryDate(LocalDate.now());
        identityVerification.setCreatedBy(identityVerification.getUserId());
        lastSavedId = this.identityVerificationService.verifyIdentity(identityVerification);
        Assertions.assertThat(lastSavedId).isGreaterThan(0);
    }

    @Test
    @Order(10)
    void saveVerificationTypeAgainstOperation() {
        OperationVerificationType operationVerificationType = new OperationVerificationType();
        operationVerificationType.setLookUpOperation(this.lookUpOperationTypeService.getOperationType(OperationType.UPDATE_MOBILE_NUMBER));
        operationVerificationType.setLookUpVerificationType(this.lookUpVerificationTypeService.getVerificationType(VerificationType.FINGER_PRINTS));
        this.operationVerificationTypeService.saveOperationVerificationType(operationVerificationType);
    }

    @Test
    @Order(11)
    void saveVerificationTypeRuleAgainstVerificationType() {
        VerificationTypeRule verificationTypeRule = new VerificationTypeRule();
        verificationTypeRule.setLookUpVerificationType(this.lookUpVerificationTypeService.getVerificationType(VerificationType.FINGER_PRINTS));
        verificationTypeRule.setType("Index_Finger");
        this.verificationTypeRuleService.saveVerificationTypeRule(verificationTypeRule);

        verificationTypeRule = new VerificationTypeRule();
        verificationTypeRule.setLookUpVerificationType(this.lookUpVerificationTypeService.getVerificationType(VerificationType.FINGER_PRINTS));
        verificationTypeRule.setType("Middle_Finger");
        this.verificationTypeRuleService.saveVerificationTypeRule(verificationTypeRule);

        verificationTypeRule = new VerificationTypeRule();
        verificationTypeRule.setLookUpVerificationType(this.lookUpVerificationTypeService.getVerificationType(VerificationType.FINGER_PRINTS));
        verificationTypeRule.setType("Ring_Finger");
        this.verificationTypeRuleService.saveVerificationTypeRule(verificationTypeRule);

        verificationTypeRule = new VerificationTypeRule();
        verificationTypeRule.setLookUpVerificationType(this.lookUpVerificationTypeService.getVerificationType(VerificationType.FINGER_PRINTS));
        verificationTypeRule.setType("Little_Finger");
        this.verificationTypeRuleService.saveVerificationTypeRule(verificationTypeRule);
    }

    @Test
    @Order(12)
    void getVerification() {
        IdentityVerification identityVerification = this.identityVerificationService.getVerifiedUser(lastSavedId);
        List<LookUpVerificationType> updateMobileNumber = this.identityVerificationService.getVerificationType(OperationType.UPDATE_MOBILE_NUMBER);
        List<LookUpVerificationType> activateId = this.identityVerificationService.getVerificationType(OperationType.ACTIVATE_ID);
        List<LookUpVerificationType> deActivateId = this.identityVerificationService.getVerificationType(OperationType.DEACTIVATE_ID);
    }

    @Test
    @Order(13)
    void updateNewMobileNumber() {
        IdentityVerification identityVerification = this.identityVerificationService.getVerifiedUser(lastSavedId);
        identityVerification.setUserId("2545149516");
        identityVerification.setNewMobileNumber("527370366");
        identityVerification.setUpdatedAt(LocalDateTime.now());
        identityVerification.setUpdatedBy(identityVerification.getUserId());
        Boolean updated = this.identityVerificationService.updateMobileNumber(identityVerification);
        Assertions.assertThat(updated).isTrue();
    }

    @Test
    @Order(20)
    void deleteIdentityVerification() {
        IdentityVerification identityVerification = this.identityVerificationService.getVerifiedUser(lastSavedId);
        Boolean deleted = this.identityVerificationService.delete(identityVerification.getGkey());
        Assertions.assertThat(deleted).isTrue();
    }

    @Test
    @Order(21)
    void deleteVerificationTypeRule() {
        Boolean deleted = this.verificationTypeRuleService.deleteAll();
        Assertions.assertThat(deleted).isTrue();
    }

    @Test
    @Order(22)
    void deleteOperationVerificationType() {
        Boolean deleted = this.operationVerificationTypeService.deleteAll();
        Assertions.assertThat(deleted).isTrue();
    }

    @Test
    @Order(23)
    void deleteLookUpVerificationType() {
        Boolean deleted = this.lookUpVerificationTypeService.deleteAll();
        Assertions.assertThat(deleted).isTrue();
    }
}