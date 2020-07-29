package com.hqy.concurrency03;

public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User fz = new User("Fu", "FUZHOU", gate);
        User fz01 = new User("HANG", "ZHANGZHOU", gate);
        User fz02 = new User("SHENG", "SHENGZHENG", gate);

        fz.start();
        fz01.start();
        fz02.start();
    }
}
