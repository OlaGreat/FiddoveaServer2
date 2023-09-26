package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Chat;
import com.fiddovea.fiddovea.dto.request.SendMessageRequest;
import com.fiddovea.fiddovea.dto.response.MessageResponse;

public interface ChatService {
    Chat chatCustomerCare(String senderId);
    MessageResponse message(SendMessageRequest sendMessageRequest, String chatId);
}
