package org.example.dailydriver.service;

import org.example.dailydriver.model.entity.Comment;
import org.example.dailydriver.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CrudService<Comment, Comment, Comment, String> {

   private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Boolean save(Comment entity) {
        return null;
    }

    @Override
    public Comment update(Comment entity, String id) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Comment findById(String id) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return List.of();
    }
}
