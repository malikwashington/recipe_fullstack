package com.ex2.recipe.controller;

import com.ex2.recipe.model.User;
import com.ex2.recipe.service.user.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceInterface userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User theUser = userService.registerUser(user);
        return ResponseEntity.ok(theUser);
    }

    @GetMapping
    public ResponseEntity<String> findUserByUsername(@RequestParam String username){
        String theUser = userService.findByUsername(username);
        return ResponseEntity.ok(theUser);
    }
}
