package com.hqy.concurrency05;


public class StringTest {

    public static void main(String[] args) {
        //String是典型的不可变对象
        String hello = "Hello";
        String replace = hello.replace("l", "k");
        System.out.println(replace);
    }

}
