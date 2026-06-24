package com.plantable.backend.service;

import com.plantable.backend.model.User;
import com.plantable.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(String auth0Id, String email, String name) {
        Optional<User> existing = userRepository.findByAuth0Id(auth0Id);
        if (existing.isPresent()) {
            return existing.get();
        }
        User newUser = new User(auth0Id, email, name);
        return userRepository.save(newUser);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByAuth0Id(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isAdmin(Long userId) {
        return userRepository.findById(userId)
                .map(User::getIsAdmin)
                .orElse(false);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
