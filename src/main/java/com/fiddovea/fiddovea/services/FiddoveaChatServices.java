package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Chat;
import com.fiddovea.fiddovea.data.models.Message;
import com.fiddovea.fiddovea.data.repository.ChatRepository;
import com.fiddovea.fiddovea.dto.request.SendMessageRequest;
import com.fiddovea.fiddovea.dto.response.MessageResponse;
import com.fiddovea.fiddovea.exceptions.FiddoveaChatException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.fiddovea.fiddovea.dto.response.ResponseMessage.CHAT_NOT_FIND;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.MESSAGE_SENT;

@Service
@AllArgsConstructor
@Slf4j
public class FiddoveaChatServices implements ChatService{

    private final ChatRepository chatRepository;

    @Override
    public Chat chatCustomerCare(String senderId) {
        Chat chat = new Chat();
        chat.setCustomerId(senderId);
        Chat savedChat = chatRepository.save(chat);

        return savedChat;
    }

    @Override
    public MessageResponse message(SendMessageRequest sendMessageRequest, String chatId ) {
        String content = sendMessageRequest.getContent();

        Chat foundChat = chatRepository.findById(chatId)
                                       .orElseThrow(()-> new FiddoveaChatException(CHAT_NOT_FIND.name()));
        Message message = new Message();
        message.setContent(content);
        foundChat.getChatHistory().add(message);
        Chat chat = chatRepository.save(foundChat);
        log.info("--------------------------->>>>>> 2222222222222" + chat);



        MessageResponse response = new MessageResponse();
        response.setMessage(MESSAGE_SENT.name());

        return response;
    }

}
