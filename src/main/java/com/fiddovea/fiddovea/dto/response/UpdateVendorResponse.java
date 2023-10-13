package com.fiddovea.fiddovea.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVendorResponse {

    private String message;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String  companyRcNumber;
    private String companyPhoneNumber;
    private String businessType;

}
