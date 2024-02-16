package com.springboot.apitest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.apitest.model.Course;
import com.springboot.apitest.model.Student;
import com.springboot.apitest.model.Teacher;
import com.springboot.apitest.repository.CourseRepository;
import com.springboot.apitest.repository.StudentRepository;
import com.springboot.apitest.repository.TeacherRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{courseId}/students/{studentId}")
    Course addStudentToCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {
        Course course = courseRepository.findById(courseId).get();
        Student student = studentRepository.findById(studentId).get();
        course.enrolledStudents.add(student);
        return courseRepository.save(course);
    }

    @PutMapping("/{courseId}/teacher/{teacherId}")
    Course assignTeacherToCourse(
            @PathVariable Long courseId,
            @PathVariable Long teacherId
    ) {
        Course course = courseRepository.findById(courseId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }
}