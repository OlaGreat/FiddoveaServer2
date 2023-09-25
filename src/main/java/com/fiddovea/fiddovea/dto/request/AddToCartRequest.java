package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {
    String productId;
    String customerId;
}
