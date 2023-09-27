package com.fiddovea.fiddovea.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequest {

    private String message;

//    @JsonProperty("to")
//    private String recipients_id;
//
//    @JsonProperty("htmlContent")
//    private String subject;
}