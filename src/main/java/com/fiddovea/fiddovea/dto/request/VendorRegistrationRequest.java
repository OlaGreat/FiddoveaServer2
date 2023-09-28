package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendorRegistrationRequest {
    private String email;
    private String password;
    private String companyRcNumber;
    private String companyPhoneNumber;
    private String businessType;
    private String houseNumber;
    private String street;
    private String lga;
    private String state;

}
