package com.example.demo.repositries;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.model.Student;
import java.util.List;

public interface studentrepo extends MongoRepository<Student, String>{
    List<Student> findByYear(int year);
    
}