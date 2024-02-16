package com.springboot.apitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.apitest.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
