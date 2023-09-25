package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveProductRequest {
    private String userId;
    private String productId;
}
