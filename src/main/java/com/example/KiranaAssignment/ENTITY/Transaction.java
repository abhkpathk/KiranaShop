package com.example.KiranaAssignment.ENTITY;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions") // Optional: Define table name if different from class name
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    // Define fields to represent transaction details
    @Column(name = "type")
    private String type; // Debit or Credit
    @Column(name = "currency")
    private String currency; // INR or USD
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "transactionDate")
    private LocalDate transactionDate;

    // Constructors, Getters, Setters, and other methods


    public Transaction(Long id, String type, String currency, BigDecimal amount, LocalDate transactionDate) {
        this.id = id;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
