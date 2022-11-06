package net.javaguides.springbootrestapi.controller;

import net.javaguides.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Arnab",
                "Das"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return  ResponseEntity.ok(student);
        return  ResponseEntity.ok().header("custom-header", "ramesh").body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Virat", "Kohli"));
        students.add(new Student(2, "Rohit", "Sharma"));

        return ResponseEntity.ok(students);

    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int id, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }


    // Spring boot Rest API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Ramesh&lastName=Ghosh
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    // Spring Boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Spring Boot REST API that handles HTTP PUT Request
    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Spring Boot REST API that handles HTTP Delete Request
    @DeleteMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        return "Student Deleted Successfully";
    }
}
