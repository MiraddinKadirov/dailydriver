package org.example.dailydriver.model.dto.commentDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateDto {

    private String content;
    @NonNull
    private String userId;
    @NonNull
    private String carId;

//    public CommentCreateDto validate(){
//
//        if (this.carId == null){
//            throw new IllegalArgumentException("Car id is required");
//        }
//        return this;
//    }

}
