package com.example.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Student;
import com.example.demo.Service.studentservice;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController{
    private final studentservice service;
    public StudentController(studentservice service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student savedStudent = service.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = service.getAllStudents();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id){
        Student student = service.getStudentById(id);
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}