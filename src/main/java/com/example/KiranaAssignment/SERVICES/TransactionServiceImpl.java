package com.example.KiranaAssignment.SERVICES;

import com.example.KiranaAssignment.DTO.TransactionRequest;
import com.example.KiranaAssignment.ENTITY.Transaction;
import com.example.KiranaAssignment.Exception.InvalidTransactionRequestException;
import com.example.KiranaAssignment.Exception.TransactionProcessingException;
import com.example.KiranaAssignment.REPOSITORY.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private final RestTemplate restTemplate;

    public TransactionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Transaction saveTranscation(TransactionRequest transactionRequest) {

        Transaction trans = new Transaction();

        if (transactionRequest == null ||
                transactionRequest.getAmount() == null ||
                transactionRequest.getCurrency() == null ||
                transactionRequest.getType() == null) {
            throw new InvalidTransactionRequestException("Please enter required inputs.");
        }

        if(!("INR".equals(transactionRequest.getCurrency()) || "USD".equals(transactionRequest.getCurrency())))
        {
            throw new InvalidTransactionRequestException("Please enter currency in INR or USD..");
        }

        if(!("Credit".equals(transactionRequest.getType()) || "Debit".equals(transactionRequest.getType())))
        {
            throw new InvalidTransactionRequestException("Please enter transaction type: Debit or Credit");
        }


           trans.setCurrency(transactionRequest.getCurrency());
           trans.setType(transactionRequest.getType());
           trans.setTransactionDate(LocalDate.now());
           BigDecimal exchangeRateForINR = exchangeRateService.getExchangeRateForINR();
           if(exchangeRateForINR ==null)
           {
               throw new TransactionProcessingException("Transaction processing failed: ");
           }
           BigDecimal amountInINR = (transactionRequest.getAmount());
           BigDecimal convertedAmount = exchangeRateForINR.multiply(amountInINR).setScale(2, RoundingMode.HALF_UP);
           trans.setAmount(convertedAmount);

           return transactionRepo.save(trans);
       }


    @Override
    public List<Transaction> findTransactionByDate(LocalDate Trasaction_Date) {

        List<Transaction> transactions = transactionRepo.findByTransactionDate(Trasaction_Date);

        for (Transaction transaction : transactions) {
            // Check if the transaction type is USD
            if ("USD".equals(transaction.getCurrency())) {
                // Get the amount and perform currency conversion from INR to USD
                BigDecimal amountInINR = transaction.getAmount(); // Assuming amount is in INR
                BigDecimal exchangeRate = exchangeRateService.getExchangeRateForINR();
                ; // Method to get the current exchange rate
                BigDecimal convertedAmount = amountInINR.divide(exchangeRate, 2, BigDecimal.ROUND_HALF_UP);

                // Set the converted amount and change the currency type to USD
                transaction.setAmount(convertedAmount);

            }

        }
        return transactions;

    }

        public BigDecimal findProfitLossByDate(LocalDate Trasaction_Date) {
        List<Transaction> TransactionForProfitLoss = findTransactionByDate(Trasaction_Date);

        double profitOrLoss = 0;
        Double totalCredit = 0.0;
        Double totalDebit = 0.0;
        for (Transaction transaction : TransactionForProfitLoss) {
            // Check if the transaction type is USD

            if ("Debit".equals(transaction.getType())) {
                totalDebit += transaction.getAmount().doubleValue();
            } else if ("Credit".equals(transaction.getType())) {
                totalCredit += transaction.getAmount().doubleValue();
            }
        }

            // Calculate profit or loss based on the difference between total Debit and total Credit
            profitOrLoss = totalCredit.doubleValue() - totalDebit.doubleValue();
        return BigDecimal.valueOf(profitOrLoss);
    }
}






