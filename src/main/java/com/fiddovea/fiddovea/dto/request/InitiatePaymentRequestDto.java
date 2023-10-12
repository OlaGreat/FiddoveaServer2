package com.fiddovea.fiddovea.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InitiatePaymentRequestDto {
    private String email;
    private Integer amount;
}

