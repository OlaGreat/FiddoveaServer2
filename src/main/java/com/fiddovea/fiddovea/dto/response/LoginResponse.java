package com.fiddovea.fiddovea.dto.response;

import com.fiddovea.fiddovea.data.models.Address;
import com.fiddovea.fiddovea.data.models.Gender;
import com.fiddovea.fiddovea.data.models.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class LoginResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
    private String email;
    private List<Address> addressList = new ArrayList<>();
    private String message;
    private String jwtToken;
}

