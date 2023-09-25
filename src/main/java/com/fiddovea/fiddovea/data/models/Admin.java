package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Setter
@Getter
@Document("admin")
public class Admin  extends User{
    @Id
    private String id;
    List<Vendor> toVerify;

}
