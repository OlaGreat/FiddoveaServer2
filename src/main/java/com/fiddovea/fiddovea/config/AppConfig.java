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

    @Value("${PAYSTACK_KEY}")
    private String payStackSecretKey;
    @Value("=${PAYSTACK_INITIATE_PAYMENT}")
    private String payStackInitiatePaymentUrl;
    @Value("${PAYSTACK_VERIFY_PAYMENT_URL}")
    private String payStackVerifyPaymentUrl;


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
