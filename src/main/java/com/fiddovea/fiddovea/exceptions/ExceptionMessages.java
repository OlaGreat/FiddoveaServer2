package com.fiddovea.fiddovea.exceptions;

public enum ExceptionMessages {

    INVALID_EMAIL("Invalid email provided"),
    INVALID_LOGIN_DETAILS("Invalid login details"),
    USER_NOT_FOUND("User not found"),
    PRODUCT_NOT_FOUND("Product not found"),
    PLEASE_FILL_ALL_FIELDS("Please provide all fields"),
    EMAIL_ALREADY_EXIST("Email already exist please login");

    ExceptionMessages(String message){ this.message = message;}

    private String message;

    public String getMessage(){return message;}


}
