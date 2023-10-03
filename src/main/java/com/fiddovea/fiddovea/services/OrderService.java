package com.fiddovea.fiddovea.services;


import com.fiddovea.fiddovea.data.models.Order;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.OrderRequest;
import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import com.fiddovea.fiddovea.dto.response.ConfirmOrderResponse;
import com.fiddovea.fiddovea.dto.response.ViewOrderResponse;

import java.util.List;

public interface OrderService {

    ViewOrderResponse viewOrderHistory(ViewOrderRequest viewOrderRequest);
    Order order(OrderRequest orderRequest, String customerId, List<Product> customerCart);

}
