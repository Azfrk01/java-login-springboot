package com.example.demo.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.model.Student;
import java.util.List;

public interface StudentRepo extends MongoRepository<Student, String>{
    List<Student> findByYear(int year);
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByBranch(String branch);
}