package com.plantable.backend.controller;

import com.plantable.backend.model.User;
import com.plantable.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createOrGetUser(@RequestBody UserRequest request) {
        User user = userService.getOrCreateUser(request.auth0Id(), request.email(), request.name());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/auth0/{auth0Id}")
    public ResponseEntity<User> getUserByAuth0Id(@PathVariable String auth0Id) {
        return userService.getUserByAuth0Id(auth0Id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    record UserRequest(String auth0Id, String email, String name) {}
}
