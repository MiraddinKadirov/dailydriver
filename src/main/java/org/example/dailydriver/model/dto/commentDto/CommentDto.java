package org.example.dailydriver.model.dto.commentDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private String id;
    private String content;
    private String username;
    private LocalDateTime createdAt;

}
