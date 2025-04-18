package org.example.dailydriver.controller;

import org.example.dailydriver.model.dto.commentDto.CommentCreateDto;
import org.example.dailydriver.model.dto.commentDto.CommentDto;
import org.example.dailydriver.model.dto.commentDto.CommentUpdateDto;
import org.example.dailydriver.model.entity.Comment;
import org.example.dailydriver.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable String id) {
        return ResponseEntity.of(Optional.ofNullable(commentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentCreateDto comment) {
        return ResponseEntity.ok(commentService.save(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable String id,
                                                    @RequestBody CommentUpdateDto comment) {
        return ResponseEntity.ok(commentService.update(comment, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable String id) {
        return ResponseEntity.ok(commentService.delete(id));
    }

}
