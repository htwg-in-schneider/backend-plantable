package com.plantable.backend.service;

import com.plantable.backend.model.Comment;
import com.plantable.backend.model.Post;
import com.plantable.backend.model.User;
import com.plantable.backend.repository.CommentRepository;
import com.plantable.backend.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    public Comment createComment(Long userId, Long postId, String content) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isEmpty() || post.isEmpty()) {
            throw new IllegalArgumentException("User or Post not found");
        }

        Comment comment = new Comment(content, user.get(), post.get());
        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long commentId, Long userId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            return false;
        }

        boolean isAuthor = comment.get().getAuthor().getId().equals(userId);
        boolean isAdmin = userService.isAdmin(userId);

        if (!isAuthor && !isAdmin) {
            throw new IllegalAccessError("Only author or admin can delete this comment");
        }

        commentRepository.deleteById(commentId);
        return true;
    }
}
