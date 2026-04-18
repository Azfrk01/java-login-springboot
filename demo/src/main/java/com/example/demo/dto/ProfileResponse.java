package com.example.demo.dto;
import java.util.Date;

public class ProfileResponse{
    private String username;
    private String role;
    private String email;
    private Date createdAt;
    public ProfileResponse(){
    }
    public ProfileResponse(String username, String role, String email, Date createdAt){
        this.username= username;
        this.role =role;
        this.email = email;
        this.createdAt=createdAt;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role= role;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email= email;
    }

    public Date getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt= createdAt;
    }
}