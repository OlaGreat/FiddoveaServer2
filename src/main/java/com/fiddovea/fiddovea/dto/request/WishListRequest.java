package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WishListRequest {
    String customerId;
    String productId;
}
