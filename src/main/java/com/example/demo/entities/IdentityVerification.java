package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "identity_verification",schema = "indentity_verification")
public class IdentityVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gkey;
    private String userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;
    private Boolean valid;
    private Boolean suspected = false;
    @Column(name = "new_mobile_number")
    private String newMobileNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private LocalDateTime createdAt = LocalDateTime.now();
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private LocalDateTime updatedAt;
    private String updatedBy;

    public Long getGkey() {
        return gkey;
    }

    public void setGkey(Long gkey) {
        this.gkey = gkey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getSuspected() {
        return suspected;
    }

    public void setSuspected(Boolean suspected) {
        this.suspected = suspected;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNewMobileNumber() {
        return newMobileNumber;
    }

    public void setNewMobileNumber(String newMobileNumber) {
        this.newMobileNumber = newMobileNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentityVerification)) return false;
        IdentityVerification that = (IdentityVerification) o;
        return Objects.equals(getGkey(), that.getGkey()) && Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getExpiryDate(), that.getExpiryDate()) && Objects.equals(getNewMobileNumber(), that.getNewMobileNumber()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getCreatedBy(), that.getCreatedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGkey(), getUserId(), getExpiryDate(), getNewMobileNumber(), getCreatedAt(), getCreatedBy());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IdentityVerification.class.getSimpleName() + "[", "]")
                .add("gkey=" + gkey)
                .add("userId='" + userId + "'")
                .add("expiryDate=" + expiryDate)
                .add("newMobileNumber='" + newMobileNumber + "'")
                .add("createdAt=" + createdAt)
                .add("createdBy='" + createdBy + "'")
                .toString();
    }
}
