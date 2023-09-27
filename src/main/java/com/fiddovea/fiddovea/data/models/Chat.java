package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Document("chat")
@ToString
public class Chat {
    @Id
    private String chatId;
    private List<Message> chatHistory = new ArrayList<>();
    private String customerId;


}
