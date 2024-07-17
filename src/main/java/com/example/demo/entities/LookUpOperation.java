package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "lookup_operation",schema = "indentity_verification")
public class LookUpOperation {
    @Id
    @Enumerated(EnumType.STRING)
    private OperationType absherCode;
    @Column(name = "ABSHER_LABEL_EN", unique = true)
    private String absherLabelEn;
    @Column(name = "ABSHER_LABEL_AR", unique = true)
    private String absherLabelAr;
    private boolean active = true;
    @Column(name = "EXTERNAL_LABEL_EN")
    private String externalLabelEn;
    @Column(name = "EXTERNAL_LABEL_AR")
    private String externalLabelAr;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private LocalDateTime createdAt = LocalDateTime.now();
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private LocalDateTime updatedAt;
    private String updatedBy;

    public OperationType getAbsherCode() {
        return absherCode;
    }

    public void setAbsherCode(OperationType absherCode) {
        this.absherCode = absherCode;
    }

    public String getAbsherLabelEn() {
        return absherLabelEn;
    }

    public void setAbsherLabelEn(String absherLabelEn) {
        this.absherLabelEn = absherLabelEn;
    }

    public String getAbsherLabelAr() {
        return absherLabelAr;
    }

    public void setAbsherLabelAr(String absherLabelAr) {
        this.absherLabelAr = absherLabelAr;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getExternalLabelEn() {
        return externalLabelEn;
    }

    public void setExternalLabelEn(String externalLabelEn) {
        this.externalLabelEn = externalLabelEn;
    }

    public String getExternalLabelAr() {
        return externalLabelAr;
    }

    public void setExternalLabelAr(String externalLabelAr) {
        this.externalLabelAr = externalLabelAr;
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
        if (!(o instanceof LookUpOperation)) return false;
        LookUpOperation that = (LookUpOperation) o;
        return isActive() == that.isActive() &&
                Objects.equals(getAbsherLabelEn(), that.getAbsherLabelEn()) &&
                Objects.equals(getAbsherLabelAr(), that.getAbsherLabelAr()) &&
                Objects.equals(getExternalLabelEn(), that.getExternalLabelEn()) &&
                Objects.equals(getExternalLabelAr(), that.getExternalLabelAr()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getCreatedBy(), that.getCreatedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbsherLabelEn(), getAbsherLabelAr(), isActive(),
                getExternalLabelEn(), getExternalLabelAr(), getCreatedAt(), getCreatedBy());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LookUpOperation.class.getSimpleName() + "[", "]")
                .add("absherCode='" + absherCode + "'")
                .add("absherLabelEn='" + absherLabelEn + "'")
                .add("absherLabelAr='" + absherLabelAr + "'")
                .add("active=" + active)
                .add("externalLabelEn='" + externalLabelEn + "'")
                .add("externalLabelAr='" + externalLabelAr + "'")
                .add("createdAt=" + createdAt)
                .add("createdBy='" + createdBy + "'")
                .toString();
    }
}
