package com.example.demo.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.model.User;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    long countByRole(String role);
}