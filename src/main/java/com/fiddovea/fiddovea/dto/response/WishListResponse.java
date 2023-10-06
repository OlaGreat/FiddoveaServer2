package com.fiddovea.fiddovea.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class WishListResponse {
    private String message;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private String productImageUrl;

}
