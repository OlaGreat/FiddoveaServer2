package com.fiddovea.fiddovea.services.payment;

import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PaystackPayStackPaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    void testPayment(){
        PaymentRequest request = new PaymentRequest();
        request.setName("Sweet cake");
        request.setAmount(150000);
        request.setDescription("get ready");
        PaymentResponse response = paymentService.makePayment(request);
        System.out.println(response.toString());

        assertNotNull(response.toString());

    }

}