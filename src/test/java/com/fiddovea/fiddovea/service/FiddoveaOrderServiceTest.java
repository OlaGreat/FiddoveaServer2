package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import com.fiddovea.fiddovea.dto.response.ViewOrderResponse;
import com.fiddovea.fiddovea.services.FiddoveaOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.fiddovea.fiddovea.dto.response.ResponseMessage.NO_ORDER_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class FiddoveaOrderServiceTest {
    @Autowired
    FiddoveaOrderService fiddoveaOrderService;

    @Test
    public void testViewOrderHistoryHasNoOrders() {
        ViewOrderRequest viewOrderRequest = new ViewOrderRequest();
        ViewOrderResponse result = fiddoveaOrderService.viewOrderHistory(viewOrderRequest);

        assertTrue(result.getOrderList().isEmpty());
        assertThat(result.getMessage()).isEqualTo(NO_ORDER_FOUND.name());
    }

}