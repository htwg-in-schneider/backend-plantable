package com.plantable.backend.service;

import com.plantable.backend.model.Post;
import com.plantable.backend.model.User;
import com.plantable.backend.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByAuthorIdOrderByCreatedAtDesc(userId);
    }

    public Post createPost(Long userId, Post postData) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        postData.setAuthor(user.get());
        return postRepository.save(postData);
    }

    public Optional<Post> updatePost(Long postId, Long userId, Post updatedData) {
        Optional<Post> existing = postRepository.findById(postId);
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        Post post = existing.get();

        boolean isAuthor = post.getAuthor().getId().equals(userId);
        boolean isAdmin = userService.isAdmin(userId);

        if (!isAuthor && !isAdmin) {
            throw new IllegalAccessError("Only author or admin can update this post");
        }

        post.setTitle(updatedData.getTitle());
        post.setContent(updatedData.getContent());
        post.setTags(updatedData.getTags());
        post.setImageUrl(updatedData.getImageUrl());

        return Optional.of(postRepository.save(post));
    }

    public boolean deletePost(Long postId, Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            return false;
        }

        boolean isAuthor = post.get().getAuthor().getId().equals(userId);
        boolean isAdmin = userService.isAdmin(userId);

        if (!isAuthor && !isAdmin) {
            throw new IllegalAccessError("Only author or admin can delete this post");
        }

        postRepository.deleteById(postId);
        return true;
    }
}
