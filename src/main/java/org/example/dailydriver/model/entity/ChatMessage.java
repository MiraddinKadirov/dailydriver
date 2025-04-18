package org.example.dailydriver.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dailydriver.model.entity.baseEntity.Identity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage extends Identity {

    private String sender;         // Kim yubordi
    private String recipient;      // Kimga
    private String content;        // Xabar matni
    private LocalDateTime timestamp; // Vaqti

}
