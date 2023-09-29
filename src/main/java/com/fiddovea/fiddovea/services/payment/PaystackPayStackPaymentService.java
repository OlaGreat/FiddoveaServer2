package com.fiddovea.fiddovea.services.payment;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PaystackPayStackPaymentService implements PaymentService {
    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        String paystackUrl = "https://api.paystack.co/page";

//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer sk_test_bf38960616f3bade6091d7a08515ba61547e5223");
//        headers.set("Content-Type", "application/json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String paymentRequestJson;
//
//        try {
//           paymentRequestJson  = objectMapper.writeValueAsString(paymentRequest);
//        }catch (JacksonException e){
//                return new PaymentResponse("Error: Failed to serialize PaymentRequest to JSON");
//        }
//
//        HttpEntity<String> request = new HttpEntity<>(paymentRequestJson, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<PaymentResponse> response =
//                restTemplate.postForEntity(paystackUrl, request, PaymentResponse.class);
//        PaymentResponse paymentResponse = response.getBody();
//        return paymentResponse;
//    }

//        String paystackUrl = "https://api.paystack.co/page";
        String apiKey = "Bearer sk_test_bf38960616f3bade6091d7a08515ba61547e5223";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest, headers);

        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<PaymentResponse> response = restTemplate.exchange(
                paystackUrl, HttpMethod.POST, request, PaymentResponse.class);
        System.out.println(response.getStatusCode());
        return response.getBody();

    }
}


