package com.plantable.backend.controller;

import com.plantable.backend.model.Comment;
import com.plantable.backend.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/community/posts/{postId}/comments")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(
            @PathVariable Long postId,
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody CommentRequest request) {
        try {
            Comment created = commentService.createComment(userId, postId, request.content());
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestHeader("X-User-Id") Long userId) {
        try {
            boolean deleted = commentService.deleteComment(commentId, userId);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalAccessError e) {
            return ResponseEntity.status(403).build();
        }
    }

    record CommentRequest(String content) {}
}
