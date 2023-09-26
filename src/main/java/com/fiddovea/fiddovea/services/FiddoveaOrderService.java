package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Order;
import com.fiddovea.fiddovea.data.repository.OrderRepository;
import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import com.fiddovea.fiddovea.dto.response.ViewOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.fiddovea.fiddovea.dto.response.ResponseMessage.NO_ORDER_FOUND;

@Service
@AllArgsConstructor
public class FiddoveaOrderService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public ViewOrderResponse viewOrderHistory(ViewOrderRequest viewOrderRequest) {
        List<Order> orders = orderRepository.findByStatus(viewOrderRequest);
        ViewOrderResponse viewOrderResponse = new ViewOrderResponse();
        if (orders.isEmpty()) {
            viewOrderResponse.setOrderList(Collections.emptyList());
            viewOrderResponse.setMessage(NO_ORDER_FOUND.name());
        } else {
            viewOrderResponse.setOrderList(orders);
        }

        return viewOrderResponse;
    }
}
