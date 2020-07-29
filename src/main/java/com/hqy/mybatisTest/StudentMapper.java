package com.hqy.mybatisTest;


import com.hqy.mybatisTest.model.Student;

@org.apache.ibatis.annotations.Mapper
public interface StudentMapper {

    Student findStudentById(String id);

    Student findSchoolById(String id);
}
