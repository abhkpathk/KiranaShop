package com.example.KiranaAssignment.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class TransactionProcessingException extends RuntimeException {
    public TransactionProcessingException(String message) {
        super(message);
    }
}
