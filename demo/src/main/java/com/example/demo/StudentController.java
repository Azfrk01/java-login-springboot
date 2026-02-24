package com.example.demo;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Student;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/students")
public class StudentController{
    private List<Student> list=new ArrayList<>();
    @PostMapping("/add")
    public String add(@RequestBody Student s){
        list.add(s);
        return "Student added";
    }
    @GetMapping
    public List<Student> showAll(){
        return list;
    }
    @GetMapping("/{id}")
    public Object showOne(@PathVariable int id){
        for(Student s:list){
            if(s.getId()==id){
                return s;
            }
        }
        return "Student not found";
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody Student s1){
        for(Student s:list){
            if(s.getId()==id){
                s.setName(s1.getName());
                s.setBranch(s1.getBranch());
                s.setYear(s1.getYear());
                return "Student updated";
            }
        }
        return "Student not found";
    }
    @DeleteMapping("/delete/{id}")
    public String remove(@PathVariable int id){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==id){
                list.remove(i);
                return "Student deleted";
            }
        }
        return "Student not found";
    }
}