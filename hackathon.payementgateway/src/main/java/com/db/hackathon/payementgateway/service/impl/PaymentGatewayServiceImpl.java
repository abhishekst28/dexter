package com.db.hackathon.payementgateway.service.impl;

import com.db.hackathon.payementgateway.dao.PaymentGatewayDao;
import com.db.hackathon.payementgateway.exception.*;
import com.db.hackathon.payementgateway.model.Account;
import com.db.hackathon.payementgateway.model.TransactionData;
import com.db.hackathon.payementgateway.service.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    @Autowired
    PaymentGatewayDao paymentGatewayDao;

    @Override
    public boolean doPayment(TransactionData transactionData) throws InvalidSenderException, InvalidReceiverException, InvalidAmountException, InvalidTokenException, IncompletePaymentException {
        paymentGatewayDao.validateSender(transactionData.getSender());
        paymentGatewayDao.validateReceiver(transactionData.getReceiver());
        paymentGatewayDao.validateAmount(transactionData.getSender(),transactionData.getAmount());
        paymentGatewayDao.validateToken(transactionData.getSender(),transactionData.getToken());

        return paymentGatewayDao.doPayment(transactionData.getSender(),transactionData.getReceiver(),transactionData.getAmount());
    }

    @Override
    public boolean setup() {
        return paymentGatewayDao.setup();
    }

    @Override
    public Account getAccount(int id) {
        return paymentGatewayDao.getAccount(id);
    }
}
