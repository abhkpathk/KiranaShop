package com.example.KiranaAssignment.SERVICES;

import com.example.KiranaAssignment.DTO.ExchangeRateApiResponse;
import com.example.KiranaAssignment.Exception.TransactionProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ExchangeRateService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal getExchangeRateForINR() {
        String apiUrl = "https://api.fxratesapi.com/latest";


            // Make a GET request to the API
            ExchangeRateApiResponse response = restTemplate.getForObject(apiUrl, ExchangeRateApiResponse.class);

            // Check if response is not null and rates map contains INR
            if (response != null && response.getRates().containsKey("INR")) {
                return response.getRates().get("INR");

            }

            return null;
            }


}
