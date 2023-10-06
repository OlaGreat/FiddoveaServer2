package com.fiddovea.fiddovea.services.payment;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiddovea.fiddovea.config.AppConfig;
import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PaystackPayStackPaymentService implements PaymentService {

    AppConfig appConfig;



    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        String authorization = "Bearer sk_test_bf38960616f3bade6091d7a08515ba61547e5223";
        String paystackUrl = "https://api.paystack.co/transaction/initialize";


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization );
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentResponse> response =
                restTemplate.postForEntity(paystackUrl,request, PaymentResponse.class);
        ModelMapper mapper = new ModelMapper();
        PaymentResponse paymentResponse = new PaymentResponse();
        mapper.map(response.getBody(), paymentResponse);

        System.out.println(response.getBody())    ;
        return paymentResponse;
    }

}













