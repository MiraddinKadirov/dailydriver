package org.example.dailydriver.mapper;

import org.example.dailydriver.model.dto.commentDto.CommentCreateDto;
import org.example.dailydriver.model.dto.commentDto.CommentDto;
import org.example.dailydriver.model.dto.commentDto.CommentUpdateDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentCreateDto dto);
    Comment toEntity(CommentUpdateDto dto);

    Comment toEntity(CommentDto dto);

    @Mapping(source = "user.username", target = "username")
    CommentDto toDto(Comment comment);

    List<CommentDto> toDto(List<Comment> comments);

    @Mapping(target = "id", ignore = true)
    void updateComment(CommentUpdateDto comment, @MappingTarget Comment target);

}
