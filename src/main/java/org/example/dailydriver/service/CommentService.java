package org.example.dailydriver.service;

import lombok.RequiredArgsConstructor;
import org.example.dailydriver.mapper.CommentMapper;
import org.example.dailydriver.model.dto.commentDto.CommentCreateDto;
import org.example.dailydriver.model.dto.commentDto.CommentDto;
import org.example.dailydriver.model.dto.commentDto.CommentUpdateDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.Comment;
import org.example.dailydriver.repository.AuthUserRepository;
import org.example.dailydriver.repository.CarRepository;
import org.example.dailydriver.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements CrudService<CommentCreateDto, CommentUpdateDto, CommentDto, String> {

    private final CommentRepository commentRepository;
    private final AuthUserRepository authUserRepository;
    private final CarRepository carRepository;
    private final CommentMapper commentMapper;


    @Override
    public CommentDto save(CommentCreateDto dto) {
        AuthUser user = authUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Comment entity = commentMapper.toEntity(dto);
        entity.setCar(car);
        entity.setUser(user);
        List<Comment> comments = new ArrayList<>();
        comments.add(entity);
        car.setComments(comments);
        carRepository.save(car);
        return commentMapper.toDto(commentRepository.save(entity));
    }

    @Override
    public CommentDto update(CommentUpdateDto entity, String id) {
        Comment comment = commentRepository.findByCarId(id);
        commentMapper.updateComment(entity, comment);
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public Boolean delete(String id) {
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public CommentDto findById(String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        return commentMapper.toDto(comment);
    }

    @Override
    public List<CommentDto> findAll() {
        return List.of();
    }

    @Override
    public List<CommentDto> findAll(String id) {
        List<Comment> comments = commentRepository.findAllByCarId(id);
        List<CommentDto> dtoList = commentMapper.toDto(comments);

        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            CommentDto dto = dtoList.get(i);
            AuthUser user = comment.getUser();
            dto.setUsername(user.getUsername());

        }

        return dtoList;
    }

}
