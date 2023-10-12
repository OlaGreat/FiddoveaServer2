package com.fiddovea.fiddovea.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBodyDetails<T> {
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public ResponseBodyDetails(String message) {
        this.message = message;
    }

    public static ResponseBodyDetails error(String message, int status) {
        return new ResponseBodyDetails(status, message, null);
    }
}

