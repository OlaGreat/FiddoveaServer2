package com.fiddovea.fiddovea.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${cloud.api.name}")
    private String cloudName;

    @Value("${cloud.api.key}")
    private String cloudKey;

    @Value("${cloud.api.secret}")
    private String cloudSecret;

    @Value("${payStack-secret-key}")
    private String payStackSecretKey;
    @Value("${payStack-initiate-payment-url}")
    private String payStackInitiatePaymentUrl;
    @Value("${payStack-verify-payment-url}")
    private String payStackVerifyPaymentUrl;

    @Value("${paystack.secret.key}")
    private String paystackSecertKey;


    public String getPayStackSecretKey(){return payStackSecretKey;}

    public String getPayStackInitiatePaymentUrl(){return payStackInitiatePaymentUrl;}
    public String getPayStackVerifyPaymentUrl(){return payStackVerifyPaymentUrl;}
    public String getCloudName() {
        return cloudName;
    }

    public String getCloudKey() {
        return cloudKey;
    }

    public String getCloudSecret() {
        return cloudSecret;
    }

}
