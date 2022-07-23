package com.kadowork.dynamodbpractice.app.controller;

import com.kadowork.dynamodbpractice.domain.entity.*;
import com.kadowork.dynamodbpractice.domain.repository.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final DynamoDBRepository repository;

    // test
    @GetMapping("/random-generate")
    public void randomGenerate() {
        createAndSaveUser();
    }

    // test
    @GetMapping("/random-generate-100")
    public void randomGenerate100() {
        for (int i = 0; i <= 100; i++) {
            createAndSaveUser();
        }
    }

    // test
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return repository.get(id, User.class);
    }

    // test
    @GetMapping("")
    public List<User> getAllUsers() {
        return repository.scan(User.class);
    }

    private void createAndSaveUser() {
        repository.save(User.builder()
                            .id(UUID.randomUUID().toString())
                            .firstName(UUID.randomUUID().toString().substring(0, 5))
                            .lastName(UUID.randomUUID().toString().substring(0, 5))
                            .createdAt(LocalDateTime.now())
                            .build(), User.class);
    }
}
