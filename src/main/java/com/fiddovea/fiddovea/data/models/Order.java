package com.fiddovea.fiddovea.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Setter
@Getter
@Builder
@Document("orders")
public class Order {
    @Id
    private String id;
    private List<Product> orderedProduct;
    private BigDecimal orderAmount;
    private Address deliveryAddress;
    private LocalDateTime deliveryDate;
    private OrderStatus status;
    private String createdAt = setCreatedAt();
    private String customerId;



    public String setCreatedAt(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
   return createdAt = localDateTime.format(formatter);
    }

}
