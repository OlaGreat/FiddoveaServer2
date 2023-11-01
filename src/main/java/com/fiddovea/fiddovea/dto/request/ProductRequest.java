package com.fiddovea.fiddovea.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductRequest {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private double discount;
    private int productQuantity;
    private MultipartFile productImage;
    private String productType;
}
