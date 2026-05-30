package com.chandasi.auth.controller;

import com.chandasi.auth.model.User;
import com.chandasi.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
// CORS handled by Gateway
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepo.findByPhone(user.getPhone()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Phone pehle se registered hai!"));
        }
        return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String phone    = req.get("phone");
        String password = req.get("password");
        Optional<User> user = userRepo.findByPhone(phone);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Phone ya password galat hai!"));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
