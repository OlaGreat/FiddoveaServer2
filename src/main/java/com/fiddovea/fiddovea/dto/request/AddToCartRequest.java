package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {
    private String productId;
    private String customerId;
}
