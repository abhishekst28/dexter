package com.db.hackathon.payementgateway.dao.impl;

import com.db.hackathon.payementgateway.dao.PaymentGatewayDao;
import com.db.hackathon.payementgateway.exception.*;
import com.db.hackathon.payementgateway.model.Account;
import com.db.hackathon.payementgateway.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class PaymentGatewayDaoImpl implements PaymentGatewayDao {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public boolean doPayment(String senderInfo, String receiverInfo, String amount) {
        try {
            Account senderAccount = accountRepository.findByPhoneNum(senderInfo);
            Account receiverAccount = accountRepository.findByPhoneNum(receiverInfo);

            int amt = Integer.parseInt(amount);

            senderAccount.setBalanceAmt((senderAccount.getBalanceAmt() - amt));
            receiverAccount.setBalanceAmt((receiverAccount.getBalanceAmt() + amt));

            accountRepository.save(senderAccount);
            accountRepository.save(receiverAccount);
        } catch (Exception e){
            throw new IncompletePaymentException("Failed to make payment now. Please try after some time.");
        }
        return true;
    }

    @Override
    public void validateSender(String senderInfo) throws InvalidSenderException {
        if(null == accountRepository.findByPhoneNum(senderInfo))
            throw new InvalidSenderException();
    }

    @Override
    public void validateReceiver(String receiverInfo) throws InvalidReceiverException {
        if(null == accountRepository.findByPhoneNum(receiverInfo))
            throw new InvalidReceiverException();
    }

    @Override
    public void validateAmount(String senderInfo, String amount) throws InvalidAmountException {
        int amt;
        try{
            amt = Integer.parseInt(amount);
        } catch (NumberFormatException e){
            throw new InvalidAmountException();
        }

        Account account = accountRepository.findByPhoneNum(senderInfo);

        if(account.getBalanceAmt()<amt || (account.getBalanceAmt()-amt)<=0)
            throw new InvalidAmountException();
    }

    @Override
    public void validateToken(String senderInfo, String token) throws InvalidTokenException {
        if(null == accountRepository.findByPhoneNumAndSecureTokenIs(senderInfo,token))
            throw new InvalidTokenException();
    }

    @Override
    public boolean setup() {
        accountRepository.save(new Account(1,"Mahesh","More","1234567890",100000,"1234"));
        accountRepository.save(new Account(2,"Megha","Bijlani","0987654321",100000,"1234"));
        accountRepository.save(new Account(3,"Anmol","Narang","0147258369",100000,"1234"));

        return true;
    }

    @Override
    public Account getAccount(int id) {
        return accountRepository.findById(id).get();
    }
}
