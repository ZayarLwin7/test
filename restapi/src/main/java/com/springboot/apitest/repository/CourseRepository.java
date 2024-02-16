package com.springboot.apitest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.apitest.model.Course;
import com.springboot.apitest.model.Student;

public interface CourseRepository extends JpaRepository<Course, Long>{
	
}
