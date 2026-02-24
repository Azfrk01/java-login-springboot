package com.example.demo;
import org.springframework.web.bind.annotation.*;
@RestController
public class HelloController {
    private String lastMessage = "Hi from Java backend!";
    @GetMapping("/hello")
    public String getMessage() {
        return lastMessage;
    }
    @PostMapping("/message")
    public String setMessage(@RequestBody String newMessage) {
        lastMessage = newMessage;
        return "Message updated!";
    }
}
