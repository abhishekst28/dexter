package com.db.hackathon.payementgateway.repositories;

import com.db.hackathon.payementgateway.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    public Account findByPhoneNum(String phoneNum);

    public Account findByPhoneNumAndSecureTokenIs(String phoneNum, String value);

}