package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Order;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.OrderRequest;
import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import com.fiddovea.fiddovea.dto.response.ViewOrderResponse;
import com.fiddovea.fiddovea.services.CustomerService;
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

    @Autowired
    CustomerService customerService;



    @Test
    void testThatOrderCanBeCreated(){
        List<Customer> customerList = customerService.getAllCustomer();
        Customer customer = customerList.get(0);
        OrderRequest request = new OrderRequest();

        List<Product> cart = new ArrayList<>(List.of(new Product(), new Product()));
        request.setOrderTotalAmount("50000");
        request.setHouseNumber("6A");
        request.setLga("Yaba");
        request.setStreet("Sabo");
        request.setState("Lagos");
        Order order =orderService.order(request, customer.getId(), cart);
        assertThat(order).isNotNull();

    }
}