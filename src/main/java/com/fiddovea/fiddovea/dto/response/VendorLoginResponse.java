package com.fiddovea.fiddovea.dto.response;

import com.fiddovea.fiddovea.data.models.Address;
import com.fiddovea.fiddovea.data.models.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@ToString
public class VendorLoginResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
    private String email;
    private String id;
    private String message;
    private String jwtToken;
    private String companyPhoneNumber;
}
