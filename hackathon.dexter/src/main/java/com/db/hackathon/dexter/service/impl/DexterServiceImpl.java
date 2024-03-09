package com.db.hackathon.dexter.service.impl;

import com.db.hackathon.dexter.dao.DexterDaoService;
import com.db.hackathon.dexter.model.Questions;
import com.db.hackathon.dexter.model.TransactionData;
import com.db.hackathon.dexter.model.User;
import com.db.hackathon.dexter.service.DexterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class DexterServiceImpl implements DexterService {

    @Autowired
    DexterDaoService dexterDaoService;

    @Value("${app.paymentgateway.url}")
    protected String serviceUrl;

    @Override
    public Boolean makePayment(TransactionData transactionData) throws URISyntaxException {
        String secureToken = dexterDaoService.getSecureToken(transactionData.getSender());
        transactionData.setToken(secureToken);

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = serviceUrl + "/paymentgateway/doPayment";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "INDIA");

        HttpEntity<TransactionData> request = new HttpEntity<>(transactionData, headers);

        ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, request, Boolean.class);

        return result.getBody();
    }

    @Override
    public List<Questions> getQuestion(Integer userId) {
        return dexterDaoService.getQuestions(userId);
    }

    @Override
    public boolean setup() {
        return dexterDaoService.setup();
    }

    @Override
    public User getUser(Integer userId) {
        return dexterDaoService.getUser(userId);
    }
}
