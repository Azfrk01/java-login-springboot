package com.example.demo.dto;
import java.util.Date;

public class UserResponse{
    private String id;
    private String username;
    private String role;
    private Date createdAt;
    public UserResponse(){
    }
    public UserResponse(String id, String username, String role, Date createdAt){
        this.id=id;
        this.username=username;
        this.role=role;
        this.createdAt=createdAt;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
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
        this.role=role;
    }
    public Date getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt=createdAt;
    }
}