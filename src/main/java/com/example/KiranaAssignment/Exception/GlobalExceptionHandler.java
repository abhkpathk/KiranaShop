package com.example.KiranaAssignment.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TransactionProcessingException.class)
    public ResponseEntity<String> handleTransactionProcessingException(TransactionProcessingException ex) {
        // Log the exception or perform any other necessary operations

        // Customize the response message
        String errorMessage = "Transaction processing failed: " + ex.getMessage();

        // Return a ResponseEntity with a standardized error message and status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    // Add more @ExceptionHandler methods for handling other types of exceptions globally
}
