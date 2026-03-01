package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.model.Student;
import com.example.demo.repositries.StudentRepo;

@Service
public class StudentService{
    private final StudentRepo repository;
    public StudentService(StudentRepo repository){
        this.repository = repository;
    }
    public Student saveStudent(Student student){
        return repository.save(student);
    }
    public List<Student> getAllStudents(){
        return repository.findAll();
    }
    public void deleteStudent(String id){
        repository.deleteById(id);
    }
    public Student getStudentById(String id){
        return repository.findById(id).orElse(null);
    }
}