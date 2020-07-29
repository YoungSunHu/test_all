package com.hqy.mybatisTest.controller;

import com.hqy.mybatisTest.model.School;
import com.hqy.mybatisTest.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @GetMapping("/{id}")
    public School findSchoolById(@PathVariable("id") String id ){
        return schoolService.findSchoolById(id);
    }


}
