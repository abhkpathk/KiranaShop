package com.example.KiranaAssignment.DTO;

import java.math.BigDecimal;

public class TransactionRequest {

    private String type; // Debit or Credit
    private BigDecimal amount;
    private String currency;

    public TransactionRequest(String type, BigDecimal amount, String currency) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
    }

    public TransactionRequest() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
