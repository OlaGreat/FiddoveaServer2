package com.fiddovea.fiddovea.appUtils;

import java.math.BigInteger;
import java.net.URI;
import java.util.Random;

public class AppUtils {

    public static final String APP_NAME = "Fiddovea";
    public static final String APP_MAIL_SENDER = "Oladipupoolamilekan2@gmail.com";
    public static final String PRODUCT_ADD_MESSAGE = "Product added successfully";
    public static final String otpSubject = "FIDDOVEA_VERIFICATION_OTP";
    public static final String JSON_PATCH_PATH_PREFIX = "/";

    public static final String BEARER = "Bearer";

    public static final String BLANK_SPACE = " ";

    public static final int SEVEN = BigInteger.valueOf(7).intValue();
    public static final String USER_ID = "userId";

    public static final String SECRET_KEY = "fiddoveasecret";
    public static final String  IMAGE_LOCATION_PATH = "C:\\Users\\DELL\\Documents\\ProjectWorks\\Fiddovea\\FiddoveaServer\\Fiddovea\\src\\test\\resources\\image\\jerry.jpg";

    public static boolean verifyEmail(String email){
        return  email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }
    public static String takeOutWhiteSpaces(String userInput){
        return userInput.toLowerCase().trim();
    }
}
