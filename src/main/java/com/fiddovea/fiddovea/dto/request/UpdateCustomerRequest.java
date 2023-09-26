package com.fiddovea.fiddovea.dto.request;


import com.fiddovea.fiddovea.data.models.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCustomerRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Gender gender;
    private String email;
    private String houseNumber;
    private String street;
    private String lga;
    private String state;
}
