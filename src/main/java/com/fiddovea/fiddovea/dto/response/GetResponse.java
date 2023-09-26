package com.fiddovea.fiddovea.dto.response;

import com.fiddovea.fiddovea.data.models.Gender;
import com.fiddovea.fiddovea.data.models.Role;
import lombok.*;

@ToString

@Builder
@AllArgsConstructor
@Setter
@Getter
public class GetResponse {


    private String fullName;
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Gender gender;
    private String email;
    private Role role;
    private String houseNumber;
    private String street;
    private String lga;
    private String state;

}