package org.equity.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private Long tradeId;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, length = 10)
    private String securityCode;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionAction action;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BuySell buySell;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Default constructor
    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor with parameters
    public Transaction(Long tradeId, Integer version, String securityCode,
                       Integer quantity, TransactionAction action, BuySell buySell) {
        this.tradeId = tradeId;
        this.version = version;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.action = action;
        this.buySell = buySell;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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

    public TransactionAction getAction() {
        return action;
    }

    public void setAction(TransactionAction action) {
        this.action = action;
    }

    public BuySell getBuySell() {
        return buySell;
    }

    public void setBuySell(BuySell buySell) {
        this.buySell = buySell;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", tradeId=" + tradeId +
                ", version=" + version +
                ", securityCode='" + securityCode + '\'' +
                ", quantity=" + quantity +
                ", action=" + action +
                ", buySell=" + buySell +
                ", timestamp=" + timestamp +
                '}';
    }
}



