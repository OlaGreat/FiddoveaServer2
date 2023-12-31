package com.fiddovea.fiddovea.exceptions;

public enum ExceptionMessages {

    INVALID_EMAIL("Invalid email provided"),
    INVALID_LOGIN_DETAILS("Invalid login details"),
    USER_NOT_FOUND("User not found"),
    PRODUCT_NOT_FOUND("Product not found"),
    THE_PROVIDED_EMAIL_IS_NOT_ATTACHED_TO_THE_TOKEN("The provided email is not attached to the token"),
    PLEASE_FILL_ALL_FIELDS("Please provide all fields"),
    TOKEN_EXPIRED_PLEASE_GENERATE_ANOTHER_TOKEN_FOR_VERIFICATION("Token expired please request for another token for verification"),
    INVALID_AUTHORIZATION_HEADER_EXCEPTION("Invalid authorization header"),
    VERIFICATION_FAILED_EXCEPTION("Verification failed"),
    EMAIL_ALREADY_EXIST("Email already exist please login");

    ExceptionMessages(String message){
        this.message = message;}

    private String message;

    public String getMessage(){return message;}


}
