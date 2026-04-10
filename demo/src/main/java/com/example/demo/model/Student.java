package com.example.demo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "students")
public class Student {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String branch;
    @Min(1)
    @Max(4)
    private Integer year;
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double cgpa;
    @Email
    private String email;
    private String phone;
    public Student(){
    }
    public Student(String id, String name, String branch, int year){
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.year = year;
        this.cgpa = 0.0;
        this.email = "";
        this.phone = "";
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public Double getCgpa(){
        return cgpa;
    }
    public void setCgpa(Double cgpa){
        this.cgpa = cgpa;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getBranch(){
        return branch;
    }
    public void setBranch(String branch){
        this.branch = branch;
    }
    public Integer getYear(){
        return year;
    }
    public void setYear(Integer year){
        this.year = year;
    }
}