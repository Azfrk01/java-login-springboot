package com.example.demo.controller;
import com.example.demo.Service.AuthService;
import com.example.demo.dto.AuthRequest;
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
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest req){
        try{
            service.registerUser(req.getUsername(), req.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered");
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest req){
        try{
            String jwt = service.loginUser(req.getUsername(), req.getPassword());
            return ResponseEntity.ok(Map.of("token", jwt));
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ex.getMessage());
        }
    }
}