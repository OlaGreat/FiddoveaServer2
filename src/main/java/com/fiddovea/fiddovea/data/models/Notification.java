package com.fiddovea.fiddovea.data.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document("notification")
public class Notification {

    @Id
    private String id;

        private List<Message> messages;
        private String userId;



}