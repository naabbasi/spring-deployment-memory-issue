package com.example.demo.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "verification_type_rule",schema = "indentity_verification")
public class VerificationTypeRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gkey;
    @JoinColumn(name = "verification_type_id")
    @ManyToOne
    private LookUpVerificationType lookUpVerificationType;
    private String type;

    public Long getGkey() {
        return gkey;
    }

    public void setGkey(Long gkey) {
        this.gkey = gkey;
    }

    public LookUpVerificationType getLookUpVerificationType() {
        return lookUpVerificationType;
    }

    public void setLookUpVerificationType(LookUpVerificationType lookUpVerificationType) {
        this.lookUpVerificationType = lookUpVerificationType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerificationTypeRule)) return false;
        VerificationTypeRule that = (VerificationTypeRule) o;
        return Objects.equals(getGkey(), that.getGkey()) &&
                Objects.equals(getLookUpVerificationType(), that.getLookUpVerificationType()) && 
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGkey(), getLookUpVerificationType(), getType());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OperationVerificationType.class.getSimpleName() + "[", "]")
                .add("gkey=" + gkey)
                .add("lookUpVerificationType=" + lookUpVerificationType)
                .add("type=" + getType())
                .toString();
    }
}
