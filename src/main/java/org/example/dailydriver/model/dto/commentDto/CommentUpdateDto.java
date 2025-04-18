package org.example.dailydriver.model.dto.commentDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentUpdateDto {

    private String id;
    private String content;

}
