package com.fiddovea.fiddovea.exceptions;

import com.fiddovea.fiddovea.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class AppGlobalExceptionHandler {

    @ExceptionHandler(value = {FiddoveaException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<?> handler(FiddoveaException fiddoveaException){
        var response = ApiResponse.builder().data(fiddoveaException.getMessage()).build();
        return ResponseEntity.badRequest().body(response);

    }
    @ExceptionHandler(value = {IOException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<?> handler(IOException exception){
        var response = ApiResponse.builder().data(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }
}
