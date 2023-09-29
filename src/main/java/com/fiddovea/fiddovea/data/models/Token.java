package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Document("token")
public class Token {
    @Id
    private String id;
    private String token;
    private String ownerEmail;
    private LocalDateTime timeCreated = LocalDateTime.now();
}
