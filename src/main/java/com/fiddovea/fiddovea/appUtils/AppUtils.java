package com.fiddovea.fiddovea.appUtils;

import java.net.URI;
import java.util.Random;

public class AppUtils {

    public static final String APP_NAME = "Fiddovea";
    public static final String APP_MAIL_SENDER = "Oladipupoolamilekan2@gmail.com";
    public static final String PRODUCT_ADD_MESSAGE = "Product added successfully";
    public static final String  IMAGE_LOCATION_PATH = "C:\\Users\\DELL\\Documents\\ProjectWorks\\Fiddovea\\FiddoveaServer\\Fiddovea\\src\\test\\resources\\image\\jerry.jpg";

    public static boolean verifyEmail(String email){
        return  email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }


}
