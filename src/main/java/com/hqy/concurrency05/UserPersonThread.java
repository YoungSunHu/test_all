package com.hqy.concurrency05;

public class UserPersonThread extends Thread {
    private Person person;


    public UserPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " print " + person.toString());
        }
    }
}
