package org.equity.dto;

import org.equity.entity.BuySell;
import org.equity.entity.TransactionAction;
import java.time.LocalDateTime;

public class TransactionResponse {
    private Long transactionId;
    private Long tradeId;
    private Integer version;
    private String securityCode;
    private Integer quantity;
    private TransactionAction action;
    private BuySell buySell;
    private LocalDateTime timestamp;

    // Default constructor
    public TransactionResponse() {}

    // Constructor with parameters
    public TransactionResponse(Long transactionId, Long tradeId, Integer version,
                               String securityCode, Integer quantity, TransactionAction action,
                               BuySell buySell, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.tradeId = tradeId;
        this.version = version;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.action = action;
        this.buySell = buySell;
        this.timestamp = timestamp;
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
}