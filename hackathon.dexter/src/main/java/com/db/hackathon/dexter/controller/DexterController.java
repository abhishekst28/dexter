package com.db.hackathon.dexter.controller;

import com.db.hackathon.dexter.model.Questions;
import com.db.hackathon.dexter.model.TransactionData;
import com.db.hackathon.dexter.model.User;
import com.db.hackathon.dexter.service.DexterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/askDexter")
public class DexterController {

    @Autowired
    DexterService dexterService;

    @PostMapping("/makePayment")
    public boolean makePayment(@RequestBody TransactionData transactionData) throws URISyntaxException {
        return dexterService.makePayment(transactionData);
    }

    @GetMapping("/getQuestion/{id}")
    public List<Questions> getQuestion(@PathVariable int id) {
        return dexterService.getQuestion(id);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id) {
        return dexterService.getUser(id);
    }

    @GetMapping("/setup")
    public boolean setup() {
        return dexterService.setup();
    }
}
