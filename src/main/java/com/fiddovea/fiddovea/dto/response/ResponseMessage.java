package com.fiddovea.fiddovea.dto.response;

public enum ResponseMessage {
    WELCOME_BACK("Welcome Back"),
    REGISTRATION_SUCCESSFUL("Registration Successful"),
    PENDING_BUSINESS_DETAIL_VERIFICATION("Pending Business Details Verification"),
    PROFILE_UPDATE_SUCCESSFUL("Profile Update Successful"),
    PRODUCT_ADDED_TO_CART("Product Added to Cart"),
    PRODUCT_ADDED_TO_WISH_LIST("Product Added To Wish List"),
    PRODUCT_ADDED("Product Added"),
    ITEM_REMOVED("Item Removed"),
    NO_ORDER_FOUND("No Order Found"),

    NOTIFICATION_SENT_SUCCESSFULLY("Notification Sent Successfully"),
    PRODUCT_DELETED_SUCCESSFULLY("Product Deleted Successfully"),
    ORDER_CREATED_BUT_NOT_CONFIRMED("Order Created But Not Confirmed"),
    VERIFICATION_SUCCESSFUL("Verification Successful"),
    CHAT_NOT_FOUND("Chat Not Found"),
    PLEASE_CHECK_YOUR_MAIL_FOR_VERIFICATION_CODE("Please Check Your Mail For Verification Code"),
    REVIEW_SUCCESSFUL_THANKS_FOR_YOUR_TIME("Review Successful Thanks For Your Time"),
    MESSAGE_SENT("Message Sent"),
    YOU_ADD_THE_PRODUCT_TO_YOUR_WISHLIST("You Added The Product To Your Wishlist"),
    YOUR_PRODUCT_HAS_BEEN_ADDED_SUCCESSFULLY("Your Product Has Been Added Successfully"),
    ADDRESS_ADDED_SUCCESSFULLY("Address Added Successfully"),
    IF_YOUR_EMAIL_IS_REGISTERED_YOU_WILL_GET_A_MESSAGE_FROM_US("If Your Email Is Registered You Will Get A Mail Form Us");

    private String message;

    ResponseMessage(String message) {
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
