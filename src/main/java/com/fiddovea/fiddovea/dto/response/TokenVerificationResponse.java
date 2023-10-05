package com.fiddovea.fiddovea.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenVerificationResponse {
    private String message;
    private String userAccessToken;
}

