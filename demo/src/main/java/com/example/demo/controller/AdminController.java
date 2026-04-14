package com.example.demo.controller;
import com.example.demo.dto.RoleUpdateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5500")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(adminService.getAllUsers());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/approve")
    public ResponseEntity<UserResponse> approveUser(@PathVariable String id){
        return ResponseEntity.ok(adminService.approveUser(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}/reject")
    public ResponseEntity<Void> rejectPendingUser(@PathVariable String id){
        adminService.rejectPendingUser(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserResponse> changeRole(
            @PathVariable String id,
            @RequestBody RoleUpdateRequest request
    ){
        return ResponseEntity.ok(adminService.changeRole(id, request.getRole()));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}