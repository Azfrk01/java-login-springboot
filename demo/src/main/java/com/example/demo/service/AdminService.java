package com.example.demo.service;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService{
    private final UserRepository userRepository;
    public AdminService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private User getCurrentAdmin(){
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null){
            throw new RuntimeException("Unauthorized");
        }
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!"ADMIN".equalsIgnoreCase(currentUser.getRole())){
            throw new RuntimeException("Forbidden");
        }
        return currentUser;
    }
    private UserResponse mapToResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
    public List<UserResponse> getAllUsers(){
        getCurrentAdmin();
        return userRepository.findAll().stream().map(this::mapToResponse).toList();
    }
    public UserResponse approveUser(String id){
        getCurrentAdmin();
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        String role = user.getRole();
        if("PENDING_STUDENT".equalsIgnoreCase(role)){
            user.setRole("STUDENT");
        }else if("PENDING_ADMIN".equalsIgnoreCase(role)){
            user.setRole("ADMIN");
        }else{
            throw new RuntimeException("User is not pending approval");
        }
        return mapToResponse(userRepository.save(user));
    }
    public void rejectPendingUser(String id){
        getCurrentAdmin();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.getRole().startsWith("PENDING")){
            throw new RuntimeException("Only pending users can be rejected");
        }
        userRepository.delete(user);
    }
    public UserResponse changeRole(String id, String newRole){
        getCurrentAdmin();
        if (newRole == null || newRole.isBlank()){
            throw new RuntimeException("Role is required");
        }
        String normalizedRole = newRole.trim().toUpperCase();
        if(!"ADMIN".equals(normalizedRole) && !"STUDENT".equals(normalizedRole)){
            throw new RuntimeException("Role must be ADMIN or STUDENT");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(normalizedRole);
        return mapToResponse(userRepository.save(user));
    }
    public void deleteUser(String id){
        User currentAdmin = getCurrentAdmin();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(currentAdmin.getId().equals(user.getId())){
            throw new RuntimeException("Admin cannot delete own account");
        }
        userRepository.delete(user);
    }
}