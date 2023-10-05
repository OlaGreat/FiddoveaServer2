package com.fiddovea.fiddovea.dto.response;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentResponse {
    private String authorization_url;
    private String message;

    private String access_code;
    private String reference;


}
