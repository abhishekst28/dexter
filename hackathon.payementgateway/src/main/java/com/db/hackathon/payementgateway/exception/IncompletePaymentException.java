package com.db.hackathon.payementgateway.exception;

public class IncompletePaymentException extends RuntimeException {

    private IncompletePaymentException(){}

    public IncompletePaymentException(String message){
        super(message);
    }

}
