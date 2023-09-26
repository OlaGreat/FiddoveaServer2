package com.fiddovea.fiddovea.dto.response;

import com.fiddovea.fiddovea.data.models.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ViewOrderResponse {

    private String message;

    List<Order> orderList = new ArrayList<>();
}