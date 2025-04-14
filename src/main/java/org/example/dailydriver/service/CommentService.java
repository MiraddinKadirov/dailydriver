package org.example.dailydriver.service;

import org.example.dailydriver.model.dto.commentDto.CommentCreateDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.Comment;
import org.example.dailydriver.repository.AuthUserRepository;
import org.example.dailydriver.repository.CarRepository;
import org.example.dailydriver.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService implements CrudService<CommentCreateDto, Comment, Comment, String> {

    private final CommentRepository commentRepository;
    private final AuthUserRepository authUserRepository;
    private final CarRepository carRepository;

    public CommentService(CommentRepository commentRepository, AuthUserRepository authUserRepository, CarRepository carRepository) {
        this.commentRepository = commentRepository;
        this.authUserRepository = authUserRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Comment save(CommentCreateDto dto) {

        AuthUser user = authUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setCar(car);

        return commentRepository.save(comment);

    }

    @Override
    public Comment update(Comment entity, String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setContent(entity.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public Boolean delete(String id) {
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public Comment findById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findAll() {
        return List.of();
    }

    @Override
    public List<Comment> findAll(String carID) {
        return commentRepository.findAllByCarIdNative(carID);
    }

    public Comment save(String carID, String userID, Comment entity) {
        AuthUser authUser = authUserRepository.findById(userID).orElse(null);
        Car car = carRepository.findById(carID).orElse(null);
        entity.setCar(car);
        entity.setUser(authUser);
        return commentRepository.save(entity);
    }

}
