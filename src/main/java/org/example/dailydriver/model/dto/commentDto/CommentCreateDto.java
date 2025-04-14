package org.example.dailydriver.model.dto.commentDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateDto {
    private String content;
    private String userId;
    private String carId;

}
