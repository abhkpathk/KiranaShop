package com.example.KiranaAssignment.REPOSITORY;

import com.example.KiranaAssignment.ENTITY.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    List<Transaction> findByTransactionDate(LocalDate transactionDate);

    List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);
}
