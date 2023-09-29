package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import com.fiddovea.fiddovea.services.Paystack;
import com.fiddovea.fiddovea.services.payment.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payment")

public class paystackController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> paystackPayment(@RequestBody PaymentRequest paymentRequest){
        PaymentResponse response = paymentService.makePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
