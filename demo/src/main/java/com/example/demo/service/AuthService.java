package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.JwtUtil;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LoginResponse;

@Service
public class AuthService{
    private final UserRepository userRepo;
    private final JwtUtil tokenUtil;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public AuthService(UserRepository userRepo, JwtUtil tokenUtil){
        this.userRepo=userRepo;
        this.tokenUtil=tokenUtil;
    }
    public User registerUser(String userName, String rawPassword){
        if(userRepo.existsByUsername(userName)){
            throw new RuntimeException("Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        newUser.setRole("STUDENT");
        newUser.setCreatedAt(new Date());
        return userRepo.save(newUser);

    }
    public LoginResponse loginUser(String userName, String rawPassword){
        User existingUser=userRepo.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(rawPassword, existingUser.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        String token = tokenUtil.createToken(userName);
        return new LoginResponse(token, existingUser.getRole());
    }
}