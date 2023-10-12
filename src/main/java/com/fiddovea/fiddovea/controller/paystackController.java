package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.dto.request.InitiatePaymentRequestDto;
import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import com.fiddovea.fiddovea.dto.response.ResponseBodyDetails;
import com.fiddovea.fiddovea.services.Paystack;
import com.fiddovea.fiddovea.services.payment.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/initiate-payment")
    public ResponseEntity<ResponseBodyDetails<?>> initiatePayStackPayment(@RequestBody InitiatePaymentRequestDto request){
        ResponseBodyDetails<?> response = paymentService.initiatePayment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/verify-payment/{reference}")
    public ResponseEntity<ResponseBodyDetails<?>> verifyPayStackPayment(@PathVariable String reference){
        ResponseBodyDetails<?> response = paymentService.verifyPayment(reference);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
