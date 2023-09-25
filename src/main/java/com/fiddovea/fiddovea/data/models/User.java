package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
//@Document("users")
public abstract class User {

//   private String id;
   private String firstName;
   private String lastName;
   private String phoneNumber;
   private String password;
   private Gender gender;
   private String email;
   private Role role;
   private Address address;


}

