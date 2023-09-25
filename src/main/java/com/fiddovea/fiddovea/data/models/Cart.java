package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Document("cart")
public class Cart {
    private String id;
    private List<Product> products = new ArrayList<>();
    private BigDecimal amount;
    private String customerId;
}
