package com.fiddovea.fiddovea.services.payment;

import com.fiddovea.fiddovea.dto.request.InitiatePaymentRequestDto;
import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;
import com.fiddovea.fiddovea.dto.response.ResponseBodyDetails;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public interface PaymentService  {
    PaymentResponse makePayment(PaymentRequest paymentRequest);
    ResponseBodyDetails initiatePayment(InitiatePaymentRequestDto request);
    ResponseBodyDetails verifyPayment(String reference);

}
