package com.hqy.concurrency02.Observer;

public class ObserverClient {
    public static void main(String[] args) {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("========================================");
        System.out.println("设置状态为10");
        subject.setState(10);
        System.out.println("设置状态为20");
        subject.setState(20);
    }
}
