package com.example.demo.Service;
import com.example.demo.model.User;
import com.example.demo.repositries.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{
    private final UserRepository userRepo;
    private final JwtUtil tokenUtil;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public AuthService(UserRepository userRepo, JwtUtil tokenUtil){
        this.userRepo = userRepo;
        this.tokenUtil = tokenUtil;
    }
    public User registerUser(String userName, String rawPassword){
        if(userRepo.existsByUsername(userName)){
            throw new RuntimeException("Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        newUser.setRole("STUDENT");
        return userRepo.save(newUser);
    }
    public String loginUser(String userName, String rawPassword){
        User existingUser = userRepo.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(rawPassword, existingUser.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return tokenUtil.createToken(userName);
    }
}