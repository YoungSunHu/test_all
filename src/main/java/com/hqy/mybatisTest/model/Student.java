package com.hqy.mybatisTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Student")
public class Student {

    @Id
    String id;
    String name;
    Integer age;
    String schoolId;

}
