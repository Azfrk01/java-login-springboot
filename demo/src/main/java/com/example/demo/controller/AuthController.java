package com.example.demo.controller;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController{
    private final AuthService service;
    public AuthController(AuthService service){
        this.service = service;
    }
@PostMapping("/register")
public ResponseEntity<Map<String, String>> registerUser(@RequestBody AuthRequest req){
        service.registerUser(req.getUsername(), req.getPassword(), req.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","User registered successfully"));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody AuthRequest req){
        LoginResponse response=service.loginUser(req.getUsername(),req.getPassword());
        return ResponseEntity.ok(response);
    }
}