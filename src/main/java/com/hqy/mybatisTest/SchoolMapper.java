package com.hqy.mybatisTest;

import com.hqy.mybatisTest.model.School;
import com.hqy.mybatisTest.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolMapper {
    School findSchoolById(String id);

    List<Student> findAllStudents();
}
