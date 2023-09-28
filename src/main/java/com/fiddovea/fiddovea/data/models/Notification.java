package com.fiddovea.fiddovea.data.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document("notification")
public class Notification {

    @Id
    private String id;

        private String message;
        private LocalDateTime timestamp;
        private String userId;


}