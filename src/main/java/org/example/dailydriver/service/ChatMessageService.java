package org.example.dailydriver.service;

import org.example.dailydriver.model.entity.ChatMessage;
import org.example.dailydriver.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository repository;

    public ChatMessageService(ChatMessageRepository repository) {
        this.repository = repository;
    }


    public ChatMessage save(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        return repository.save(message);
    }

    public List<ChatMessage> getAllMessages() {
        return repository.findAllByOrderByTimestampAsc();
    }

}
