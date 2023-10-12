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
    private String email;
}
