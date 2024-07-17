package com.example.demo.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "operation_verification_type",schema = "indentity_verification")
public class OperationVerificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gkey;
    @JoinColumn(name = "operation_id")
    @ManyToOne
    private LookUpOperation lookUpOperation;
    @JoinColumn(name = "verification_type_id")
    @ManyToOne
    private LookUpVerificationType lookUpVerificationType;

    public Long getGkey() {
        return gkey;
    }

    public void setGkey(Long gkey) {
        this.gkey = gkey;
    }

    public LookUpOperation getLookUpOperation() {
        return lookUpOperation;
    }

    public void setLookUpOperation(LookUpOperation lookUpOperation) {
        this.lookUpOperation = lookUpOperation;
    }

    public LookUpVerificationType getLookUpVerificationType() {
        return lookUpVerificationType;
    }

    public void setLookUpVerificationType(LookUpVerificationType lookUpVerificationType) {
        this.lookUpVerificationType = lookUpVerificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperationVerificationType)) return false;
        OperationVerificationType that = (OperationVerificationType) o;
        return Objects.equals(getGkey(), that.getGkey()) && Objects.equals(getLookUpOperation(), that.getLookUpOperation()) &&
                Objects.equals(getLookUpVerificationType(), that.getLookUpVerificationType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGkey(), getLookUpOperation(), getLookUpVerificationType());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OperationVerificationType.class.getSimpleName() + "[", "]")
                .add("gkey=" + gkey)
                .add("lookUpOperation=" + lookUpOperation)
                .add("lookUpVerificationType=" + lookUpVerificationType)
                .toString();
    }
}
