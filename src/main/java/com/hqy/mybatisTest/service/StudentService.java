package com.hqy.mybatisTest.service;

import com.hqy.mybatisTest.StudentMapper;
import com.hqy.mybatisTest.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public Student findStudentById(String id) {
        return studentMapper.findStudentById(id);
    }
}
