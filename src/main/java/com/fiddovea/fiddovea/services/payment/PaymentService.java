package com.fiddovea.fiddovea.services.payment;

import com.fiddovea.fiddovea.dto.request.PaymentRequest;
import com.fiddovea.fiddovea.dto.response.PaymentResponse;

public interface PaymentService  {
    PaymentResponse makePayment(PaymentRequest paymentRequest);
}
