package com.example.KiranaAssignment.CONTROLLER;

import com.example.KiranaAssignment.DTO.TransactionRequest;
import com.example.KiranaAssignment.ENTITY.Transaction;
import com.example.KiranaAssignment.Exception.InvalidTransactionRequestException;
import com.example.KiranaAssignment.Exception.TransactionProcessingException;
import com.example.KiranaAssignment.SERVICES.ExchangeRateService;
import com.example.KiranaAssignment.SERVICES.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    ExchangeRateService exchangeRateService;

    @PostMapping("/record")
    public ResponseEntity<String> recordTransaction(@RequestBody TransactionRequest request) {
    try{
        transactionService.saveTranscation(request);
        return ResponseEntity.ok("Transaction recorded successfully.");

    } catch (InvalidTransactionRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (TransactionProcessingException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    }

    @GetMapping("/getrecord")
    public List<Transaction> getRecordsByDate(@RequestParam("date") String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Convert the date String to a LocalDate object
        LocalDate requestedDate = LocalDate.parse(date,formatter);

        // Fetch records from database based on the requested date
        List<Transaction> records = transactionService.findTransactionByDate(requestedDate);

        return records;
    }

    @GetMapping("/getprofitloss")
    public ResponseEntity<String> getProfitLossByDate(@RequestParam("date") String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Convert the date String to a LocalDate object
        LocalDate requestedDate = LocalDate.parse(date,formatter);

        // Fetch records from your data source based on the requested date
        Double profitOrLoss = transactionService.findProfitLossByDate(requestedDate).doubleValue();

        String responseMessage;
        if (profitOrLoss > 0) {
            responseMessage = "The total profit on "+date+ " is :: "+ profitOrLoss + " INR.";
        } else if (profitOrLoss < 0) {
            responseMessage = "The total loss on "+date+ " is :: "+ profitOrLoss + " INR.";
        } else {
            responseMessage = "No Profit, No Loss on " + date+" ";
        }

        // Return response with appropriate HTTP status
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @GetMapping("/getrecords")
    public ResponseEntity<List<Transaction>> getRecordsBetweendates(  @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Transaction> transactions = transactionService.findByTransactionDateBetween(startDate, endDate);

        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


}
