package org.example.dailydriver.controller.mvc;

import org.example.dailydriver.model.dto.chatMessageDto.ChatMessageDto;
import org.example.dailydriver.model.entity.ChatMessage;
import org.example.dailydriver.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageDto chatMessageDto) {
        ChatMessage message = new ChatMessage();
        message.setSender(chatMessageDto.getSender());
        message.setRecipient(chatMessageDto.getRecipient());
        message.setContent(chatMessageDto.getContent());
        ChatMessage saved = chatMessageService.save(message);

        messagingTemplate.convertAndSend("/topic/messages", saved);
    }

    @GetMapping("/chat/history")
    @ResponseBody
    public List<ChatMessage> getChatHistory() {
        return chatMessageService.getAllMessages();
    }

}
