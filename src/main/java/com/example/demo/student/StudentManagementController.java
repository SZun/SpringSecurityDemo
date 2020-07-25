package com.example.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudent(){
        System.out.println("getAllStudent");
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student toAdd){
        System.out.println("registerNewStudent");
        System.out.println(toAdd);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable Integer id){
        System.out.println("deleteStudent");
        System.out.println(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student toEdit){
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", id, toEdit));
    }

}
