package com.fiddovea.fiddovea.dto.request;

import com.fiddovea.fiddovea.data.models.Address;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateVendorRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String  companyRcNumber;
    private String companyPhoneNumber;
    private String businessType;



}
