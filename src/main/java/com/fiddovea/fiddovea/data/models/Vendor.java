package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document("Vendor")
public class Vendor extends User{
    @Id
    private String id;
    private String companyRcNumber;
    private Address companyAddress;
    private String companyPhoneNumber;
    private BusinessType businessType;
    private List<Product> productList = new ArrayList<>();
    private List<Product> orders = new ArrayList<>();
    private boolean isActive;
    private Notification notification;


}
