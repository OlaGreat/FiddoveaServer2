package com.fiddovea.fiddovea.services.payment;

import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PaystackPayStackPaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    void testPayment() throws IOException {
        PaymentRequest request = new PaymentRequest();
        request.setEmail("Oladipupoolamilekan2@gmail.com");
        request.setAmount(150000);
        PaymentResponse response = paymentService.makePayment(request);
        System.out.println(response);

        assertNotNull(response.toString());

    }

}