package com.fiddovea.fiddovea.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class NotificationResponse {
    private String message;
}