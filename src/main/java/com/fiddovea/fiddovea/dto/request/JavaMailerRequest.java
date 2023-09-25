package com.fiddovea.fiddovea.dto.request;

import com.fiddovea.fiddovea.appUtils.AppUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JavaMailerRequest {
    private String to;
    private String subject;
    private String message;
    private String from = AppUtils.APP_MAIL_SENDER;

}
