package com.fiddovea.fiddovea.services.payment;


import com.fiddovea.fiddovea.config.AppConfig;
import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PaystackPayStackPaymentService implements PaymentService {

    AppConfig appConfig;

    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        String paystackUrl = "https://api.paystack.co/transaction/initialize";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + appConfig.getPaystackSecertKey());
        headers.setContentType(MediaType.valueOf("application/json"));

        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentResponse> response =
                restTemplate.postForEntity(paystackUrl,request, PaymentResponse.class);
        PaymentResponse paymentResponse = response.getBody();
        return paymentResponse;


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


        //NOT MY

//        String paystackUrl = "https://api.paystack.co/page";
//        String apiKey = "Bearer sk_test_bf38960616f3bade6091d7a08515ba61547e5223";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", apiKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//
//        ResponseEntity<PaymentResponse> response = restTemplate.exchange(
//                paystackUrl, HttpMethod.POST, request, PaymentResponse.class);
//        System.out.println(response.getStatusCode());
//        return response.getBody();

//    }
    }
}


