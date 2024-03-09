package com.db.hackathon.payementgateway.dao;

import com.db.hackathon.payementgateway.exception.*;
import com.db.hackathon.payementgateway.model.Account;

public interface PaymentGatewayDao {
    boolean doPayment(String senderInfo, String receiverInfo, String amount) throws IncompletePaymentException;

    void validateSender(String senderInfo) throws InvalidSenderException;

    void validateReceiver(String receiverInfo) throws InvalidReceiverException;

    void validateAmount(String senderInfo, String amount) throws InvalidAmountException;

    void validateToken(String senderInfo, String token) throws InvalidTokenException;

    boolean setup();

    Account getAccount(int id);
}
