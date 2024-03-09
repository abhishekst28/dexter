package com.db.hackathon.payementgateway.controller;

import com.db.hackathon.payementgateway.exception.*;
import com.db.hackathon.payementgateway.model.Account;
import com.db.hackathon.payementgateway.model.TransactionData;
import com.db.hackathon.payementgateway.service.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentgateway")
public class PaymentGatewayController {

    @Autowired
    PaymentGatewayService paymentGatewayService;

    @GetMapping("/setup")
    public boolean setup() {
        return paymentGatewayService.setup();
    }

    @PostMapping("/doPayment")
    public boolean doPayment(@RequestBody TransactionData transactionData) throws InvalidTokenException, InvalidAmountException, InvalidSenderException, InvalidReceiverException, IncompletePaymentException {
        return paymentGatewayService.doPayment(transactionData);
    }

    @GetMapping("/getAccount/{id}")
    public Account getAccount(@PathVariable int id) {
        return paymentGatewayService.getAccount(id);
    }

    @ExceptionHandler(value = InvalidSenderException.class)
    public ResponseEntity<Object> exception(InvalidSenderException exception) {
        return new ResponseEntity<>("Invalid sender information", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidReceiverException.class)
    public ResponseEntity<Object> exception(InvalidReceiverException exception) {
        return new ResponseEntity<>("Invalid receiver information", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidAmountException.class)
    public ResponseEntity<Object> exception(InvalidAmountException exception) {
        return new ResponseEntity<>("You do have sufficient balance to make transaction", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<Object> exception(InvalidTokenException exception) {
        return new ResponseEntity<>("Invalid token information sent", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IncompletePaymentException.class)
    public ResponseEntity<Object> exception(IncompletePaymentException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
