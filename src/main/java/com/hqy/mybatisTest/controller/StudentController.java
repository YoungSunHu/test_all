package com.hqy.mybatisTest.controller;

import com.hqy.mybatisTest.model.Student;
import com.hqy.mybatisTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable("id") String id) {
        return studentService.findStudentById(id);
    }


}
