package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("user")
public class Token {
    @Id
    private String id;
    private String token;
    private String ownerId;
}
