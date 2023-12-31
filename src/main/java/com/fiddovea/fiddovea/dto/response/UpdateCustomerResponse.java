package com.fiddovea.fiddovea.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerResponse {
    private String message;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

}
