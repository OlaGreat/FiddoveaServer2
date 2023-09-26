package com.fiddovea.fiddovea.dto.request;

import com.fiddovea.fiddovea.data.models.Address;
import com.fiddovea.fiddovea.data.models.OrderStatus;
import com.fiddovea.fiddovea.data.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ViewOrderRequest {

    private List<Product> orderedProduct;
    private BigDecimal orderAmount;
    private Address deliveryAddress;
    private LocalDateTime deliveryDate;
    private OrderStatus status;
}