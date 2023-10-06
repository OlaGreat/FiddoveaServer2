package com.fiddovea.fiddovea.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AddToCartResponse {
    private String message;
    private String productName;
    private String productId;
    private BigDecimal productPrice;
    private String productImageUrl;
}
