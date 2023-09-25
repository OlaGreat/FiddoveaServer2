package com.fiddovea.fiddovea.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document("customer")
public class Customer extends User{
    @Id
    private String id;
    private Cart cart;
    private List<Order> orders = new ArrayList<>();
    private List<Address> addressList = new ArrayList<>();
    private List<Product> wishList = new ArrayList<>();
    private boolean isActive;
}
