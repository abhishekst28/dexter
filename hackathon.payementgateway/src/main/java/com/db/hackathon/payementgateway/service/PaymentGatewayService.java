package com.db.hackathon.payementgateway.service;

import com.db.hackathon.payementgateway.exception.*;
import com.db.hackathon.payementgateway.model.Account;
import com.db.hackathon.payementgateway.model.TransactionData;

public interface PaymentGatewayService {
    boolean doPayment(TransactionData transactionData) throws InvalidSenderException, InvalidReceiverException, InvalidAmountException, InvalidTokenException, IncompletePaymentException;

    boolean setup();

    Account getAccount(int id);
}
