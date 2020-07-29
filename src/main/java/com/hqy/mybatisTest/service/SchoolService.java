package com.hqy.mybatisTest.service;

import com.hqy.mybatisTest.SchoolMapper;
import com.hqy.mybatisTest.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    public School findSchoolById(String id) {
        return schoolMapper.findSchoolById(id);
    }

}
