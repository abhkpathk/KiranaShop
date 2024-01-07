package com.example.KiranaAssignment.SERVICES;

import com.example.KiranaAssignment.DTO.TransactionRequest;
import com.example.KiranaAssignment.ENTITY.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    Transaction saveTranscation(TransactionRequest transaction);

    List<Transaction> findTransactionByDate(LocalDate transactionDate);

    BigDecimal findProfitLossByDate(LocalDate transactionDate);
}
