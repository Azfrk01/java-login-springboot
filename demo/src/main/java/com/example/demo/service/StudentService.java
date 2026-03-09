package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.model.Student;
import com.example.demo.repositries.StudentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.dto.StatsResponse;

@Service
public class StudentService{
    private final StudentRepo repository;
    public StudentService(StudentRepo repository){
        this.repository=repository;
    }
    public Student saveStudent(Student student){
        return repository.save(student);
    }
    public Page<Student> getAllStudents(Pageable pageable){
   return repository.findAll(pageable); 
    }
    public void deleteStudent(String id){
        repository.deleteById(id);
    }
    public Student getStudentById(String id){
        return repository.findById(id).orElse(null);
    }
    public Student updateStudent(String id,Student studentDetails){
        Student student=repository.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setBranch(studentDetails.getBranch());
        student.setYear(studentDetails.getYear());
        return repository.save(student);
    }
    public List<Student> searchByName(String name){
        return repository.findByNameContainingIgnoreCase(name);
    }
    public StatsResponse getStats(){
        List<Student> students = repository.findAll();
        long total = students.size();
        Map<String, Long> branchCounts =students.stream().collect(Collectors.groupingBy(Student::getBranch,Collectors.counting()));
        Map<Integer, Long> yearCounts =students.stream().collect(Collectors.groupingBy(Student::getYear,Collectors.counting()));
        StatsResponse stats = new StatsResponse();
        stats.setTotalStudents(total);
        stats.setBranchCounts(branchCounts);
        stats.setYearCounts(yearCounts);
        return stats;
    }
}