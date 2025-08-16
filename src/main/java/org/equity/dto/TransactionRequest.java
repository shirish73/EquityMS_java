package org.equity.dto;

import org.equity.entity.BuySell;
import org.equity.entity.TransactionAction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TransactionRequest {

    @NotNull(message = "Trade ID is required")
    private Long tradeId;

    @NotNull(message = "Security code is required")
    @Size(min = 1, max = 10, message = "Security code must be between 1 and 10 characters")
    private String securityCode;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotNull(message = "Action is required")
    private TransactionAction action;

    @NotNull(message = "Buy/Sell is required")
    private BuySell buySell;

    public TransactionRequest() {}

    public TransactionRequest(Long tradeId, String securityCode, Integer quantity,
                              TransactionAction action, BuySell buySell) {
        this.tradeId = tradeId;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.action = action;
        this.buySell = buySell;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
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
        this.quantity = quantity = quantity;
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
}

