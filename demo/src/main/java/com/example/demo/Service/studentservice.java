package com.example.demo.Service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.model.Student;
import com.example.demo.repositries.studentrepo;

@Service
public class studentservice {

    private final studentrepo repository;

    public studentservice(studentrepo repository) {
        this.repository = repository;
    }

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void deleteStudent(String id) {
        repository.deleteById(id);
    }

    public Student getStudentById(String id) {
        return repository.findById(id).orElse(null);
    }
}