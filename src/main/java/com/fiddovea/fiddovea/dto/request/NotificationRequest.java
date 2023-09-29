package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class NotificationRequest {

    private List<String> message;

//    @JsonProperty("to")
//    private String recipients_id;
//
//    @JsonProperty("htmlContent")
//    private String subject;
}