package com.fiddovea.fiddovea.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddAddressRequest {
    private String houseNumber;
    private String street;
    private String lga;
    private String state;
}
