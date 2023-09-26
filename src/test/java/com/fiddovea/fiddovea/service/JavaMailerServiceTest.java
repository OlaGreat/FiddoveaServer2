package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.dto.request.JavaMailerRequest;
import com.fiddovea.fiddovea.services.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JavaMailerServiceTest {
    @Autowired
    MailService mailService;

    @Test
    public void testJavaMailerSender(){
        JavaMailerRequest request = new JavaMailerRequest();
        request.setTo("coutinhodacruz10@gmail.com");
        request.setMessage("How are you");
        request.setSubject("Testing");
        mailService.send(request);
    }

}
