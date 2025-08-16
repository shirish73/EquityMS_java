package org.equity.entity;

import jakarta.persistence.*;

@Entity
public class Position {

    @Id
    private String securityCode;
    private Integer quantity;

    // Default constructor
    public Position() {}

    public Position(String securityCode, Integer quantity) {
        this.securityCode = securityCode;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Position{" +
                "securityCode='" + securityCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}