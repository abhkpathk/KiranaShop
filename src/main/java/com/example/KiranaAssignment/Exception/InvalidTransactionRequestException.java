package com.example.KiranaAssignment.Exception;

public class InvalidTransactionRequestException extends RuntimeException {
    public InvalidTransactionRequestException(String message) {
        super(message);
    }
}
