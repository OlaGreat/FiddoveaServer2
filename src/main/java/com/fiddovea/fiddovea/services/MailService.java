package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.dto.request.JavaMailerRequest;

public interface MailService {
    void send (JavaMailerRequest javaMailerRequest);
}
