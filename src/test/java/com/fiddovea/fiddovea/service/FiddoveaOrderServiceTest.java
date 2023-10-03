package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Order;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.OrderRequest;
import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import com.fiddovea.fiddovea.dto.response.ViewOrderResponse;
import com.fiddovea.fiddovea.services.FiddoveaOrderService;
import com.fiddovea.fiddovea.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.fiddovea.fiddovea.dto.response.ResponseMessage.NO_ORDER_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class FiddoveaOrderServiceTest {
    @Autowired
    FiddoveaOrderService fiddoveaOrderService;

    @Autowired
    OrderService orderService;

    @Test
    public void testViewOrderHistoryHasNoOrders() {
        ViewOrderRequest viewOrderRequest = new ViewOrderRequest();
        System.out.println(viewOrderRequest);
        ViewOrderResponse result = fiddoveaOrderService.viewOrderHistory(viewOrderRequest);

        assertTrue(result.getOrderList().isEmpty());
        assertThat(result.getMessage()).isEqualTo(NO_ORDER_FOUND.name());
    }

    @Test
    void testThatOrderCanBeCreated(){
        OrderRequest request = new OrderRequest();

        List<Product> cart = new ArrayList<>(List.of(new Product(), new Product()));
        request.setOrderTotalAmount("50000");
        request.setHouseNumber("6A");
        request.setLga("Yaba");
        request.setStreet("Sabo");
        request.setState("Lagos");
        Order order =orderService.order(request, "650f84e1e7e3ce5c035725e4", cart);
        System.out.println(order);
        assertThat(order).isNotNull();

    }
}