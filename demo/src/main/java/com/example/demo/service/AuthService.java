package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.JwtUtil;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.ProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public User registerUser(String userName, String email, String rawPassword, String role){
        if (userRepo.existsByUsername(userName)) {
            throw new RuntimeException("Username already exists");
        }
        String requestedRole = role == null ? "STUDENT" : role.trim().toUpperCase();
        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setEmail(email == null ? null : email.trim());
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        newUser.setCreatedAt(new Date());
        if ("ADMIN".equals(requestedRole)){
            long approvedAdminCount = userRepo.countByRole("ADMIN");
            if (approvedAdminCount == 0) {
                newUser.setRole("ADMIN");
            } else {
                newUser.setRole("PENDING_ADMIN");
            }
        }else{
            newUser.setRole("PENDING_STUDENT");
        }
        return userRepo.save(newUser);
    }
    public LoginResponse loginUser(String userName, String rawPassword){
        User existingUser = userRepo.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(rawPassword, existingUser.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        if (existingUser.getRole() != null && existingUser.getRole().startsWith("PENDING")){
            throw new RuntimeException("Account pending approval.");
        }
        String token = tokenUtil.createToken(
                existingUser.getUsername(),
                existingUser.getRole()
        );
        return new LoginResponse(token, existingUser.getRole());
    }
    public ProfileResponse getCurrentUserProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null){
            throw new RuntimeException("Unauthorized");
        }
        String username = authentication.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new ProfileResponse(
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
    public void changePassword(String oldPassword, String newPassword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null){
            throw new RuntimeException("Unauthorized");
        }
        String username = authentication.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }
}