package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ToString
@Document("products")
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private double discount;
    private List<Review> productReviews;
    private int productQuantity;
    private String vendorId;
    private byte[] productImage;
}
