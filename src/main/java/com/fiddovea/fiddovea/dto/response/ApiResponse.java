package com.fiddovea.fiddovea.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ApiResponse <T> {
    private T data;
}
