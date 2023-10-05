package com.fiddovea.fiddovea.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("amount")
    private  int amount;

}
