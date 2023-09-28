package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String name;
    private  int amount;
    private String description;
}
