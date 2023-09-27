package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Chat;
import com.fiddovea.fiddovea.dto.request.SendMessageRequest;
import com.fiddovea.fiddovea.services.ChatService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChatServiceTest {
    @Autowired
    ChatService chatService;

    @Test
    @Order(1)
    void testThatChatCanBeOpen(){
        Chat chat = chatService.chatCustomerCare("650f84e1e7e3ce5c035725e4");
        assertThat(chat).isNotNull();
    }
    @Test
    @Order(2)
    void testThatUserCanSendMessage(){
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setContent("I Love Fiddovea service");
        chatService.message(sendMessageRequest, "65126d75777092057de4323f");
    }

}
