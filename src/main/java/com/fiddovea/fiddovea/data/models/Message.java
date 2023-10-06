package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class Message {
    private String content;
    private String sendAt = sentAt();


    private String sentAt(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
