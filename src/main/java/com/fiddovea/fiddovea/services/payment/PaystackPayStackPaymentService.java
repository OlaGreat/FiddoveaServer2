package com.fiddovea.fiddovea.services.payment;


import com.fiddovea.fiddovea.config.AppConfig;
import com.fiddovea.fiddovea.dto.request.InitiatePaymentRequestDto;
import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PayStackData;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import com.fiddovea.fiddovea.dto.response.ResponseBodyDetails;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.json.JSONObject;
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
    public ResponseBodyDetails<?> initiatePayment(InitiatePaymentRequestDto initiatePaymentRequestDto) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("amount", initiatePaymentRequestDto.getAmount().toString())
                .add("email", initiatePaymentRequestDto.getEmail())
                .build();

        System.out.println(appConfig.getPayStackInitiatePaymentUrl());

        Request request = new Request.Builder()
                .url(appConfig.getPayStackInitiatePaymentUrl())
                .header("Authorization", "Bearer " + appConfig.getPayStackSecretKey())
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                assert response.body() != null;
                throw new Exception("PayStack request failed: " + response.body().string());
            }
            assert response.body() != null;

//            return ResponseBodyDetails.builder()
//                    .message("Payment successful")
//                    .status(200)
//                    .data(response.body().string())
//                    .build();

            String jsonResponse = response.body().string();
            return convertStringToJsonObject(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while initiating payment request --> " + e.getMessage());
        }
    }

    private ResponseBodyDetails<?> convertStringToJsonObject(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        boolean status = jsonObject.getBoolean("status");
        String message = jsonObject.getString("message");
        JSONObject data = jsonObject.getJSONObject("data");

        String authorizationUrl = data.getString("authorization_url");
        String accessCode = data.getString("access_code");
        String reference = data.getString("reference");

        PayStackData payStackData = PayStackData.builder()
                .authorizationUrl(authorizationUrl)
                .accessCode(accessCode)
                .reference(reference)
                .build();
        return ResponseBodyDetails.builder()
                .message(message)
                .status(200)
                .data(payStackData)
                .build();
    }

    @Override
    public ResponseBodyDetails<?> verifyPayment(String reference) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(appConfig.getPayStackVerifyPaymentUrl() + reference)
                .header("Authorization", "Bearer " + appConfig.getPayStackSecretKey())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                assert response.body() != null;
                throw new Exception("PayStack payment verification request failed: " + response.body().string());
            }
            assert response.body() != null;
            String responseBody = response.body().string();
            return ResponseBodyDetails.builder()
                    .message("PayStack payment verification request successful")
                    .status(200)
                    .data(responseBody)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while initiating payment request --> " + e.getMessage());
        }
    }

    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        String authorization = "Bearer sk_test_bf38960616f3bade6091d7a08515ba61547e5223";
        String paystackUrl = "https://api.paystack.co/transaction/initialize";


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentResponse> response =
                restTemplate.postForEntity(paystackUrl, request, PaymentResponse.class);

        PaymentResponse paymentResponse = response.getBody();


        return paymentResponse;
    }


}














