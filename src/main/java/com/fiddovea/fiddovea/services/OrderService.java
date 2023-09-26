package com.fiddovea.fiddovea.services;


import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import com.fiddovea.fiddovea.dto.response.ViewOrderResponse;

public interface OrderService {

    ViewOrderResponse viewOrderHistory(ViewOrderRequest viewOrderRequest);
}
