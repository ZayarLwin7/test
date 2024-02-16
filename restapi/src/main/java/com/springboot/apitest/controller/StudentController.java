package com.springboot.apitest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.apitest.model.Course;
import com.springboot.apitest.model.Student;
import com.springboot.apitest.repository.CourseRepository;
import com.springboot.apitest.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
    
    @PutMapping("/{studentId}/enroll/course/{courseId}")
    public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        Student student = studentRepository.findById(studentId).get();

            if (student.getCourses().contains(course)) {
                return ResponseEntity.badRequest().body("Student with ID " + studentId + " is already enrolled in course with ID " + courseId);
            }

            // Add the course to the student's enrolled courses
            student.getCourses().add(course);
            
            //System.out.println(course);

            // Save the updated student
            studentRepository.save(student);

            return ResponseEntity.ok("Student with ID " + studentId + " enrolled in course with ID " + courseId + " successfully.");
        
    }
}