package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.*;
import com.fiddovea.fiddovea.data.repository.OrderRepository;
import com.fiddovea.fiddovea.dto.request.OrderRequest;
import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import com.fiddovea.fiddovea.dto.response.ConfirmOrderResponse;
import com.fiddovea.fiddovea.dto.response.ViewOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.fiddovea.fiddovea.data.models.OrderStatus.UNCONFIRMED;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.NO_ORDER_FOUND;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.ORDER_CREATED_BUT_NOT_CONFIRMED;

@Service
@AllArgsConstructor
public class FiddoveaOrderService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order order(OrderRequest orderRequest, String customerId, List<Product> customerCart) {
        String orderTotalAmount = orderRequest.getOrderTotalAmount();

        Address deliveryAddress = Address.builder()
                .state(orderRequest.getState())
                .street(orderRequest.getStreet())
                .houseNumber(orderRequest.getStreet())
                .lga(orderRequest.getLga())
                .houseNumber(orderRequest.getHouseNumber())
                .build();




        Order order = new Order();
        order.setOrderAmount(new BigDecimal(orderTotalAmount));
        order.setOrderedProduct(customerCart);
        order.setStatus(UNCONFIRMED);
        order.setCustomerId(customerId);
        order.setDeliveryAddress(deliveryAddress);

        Order savedOrder = orderRepository.save(order);

      return savedOrder;
    }
}
