package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.JwtUtil;
import com.example.demo.dto.LoginResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AuthService{
    private final UserRepository userRepo;
    private final JwtUtil tokenUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepo,
                       JwtUtil tokenUtil,
                       BCryptPasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.tokenUtil = tokenUtil;
        this.passwordEncoder = passwordEncoder;
    }
    public User registerUser(String userName,
                             String rawPassword,
                             String role){
        if (userRepo.existsByUsername(userName)){
            throw new RuntimeException("Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        newUser.setRole("STUDENT");
        newUser.setCreatedAt(new Date());
        return userRepo.save(newUser);
    }
    public LoginResponse loginUser(String userName,
                                   String rawPassword){
        User existingUser = userRepo.findByUsername(userName)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        if (!passwordEncoder.matches(
                rawPassword,
                existingUser.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        String token = tokenUtil.createToken(
                existingUser.getUsername(),
                existingUser.getRole()
        );
        return new LoginResponse(
                token,
                existingUser.getRole()
        );
    }
}