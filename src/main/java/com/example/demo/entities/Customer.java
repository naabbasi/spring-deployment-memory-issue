package com.example.demo.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gkey")
    private Long gkey;
    @Column(name = "customer_name")
    private String customerName;
    
    public Long getGkey() {
        return gkey;
    }
    
    public void setGkey(Long gkey) {
        this.gkey = gkey;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gkey, customerName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return Objects.equals(gkey, other.gkey) && Objects.equals(customerName, other.customerName);
    }

    @Override
    public String toString() {
        return "Customer [gkey=" + gkey + ", customerName=" + customerName + "]";
    }
}
