package com.hqy.concurrency.threadGroup;

public class ThreadGroupCreate {

    public static void main(String[] args) {

        ThreadGroup threadGroup01 = new ThreadGroup("TG1");
        //
        Thread thread = new Thread(threadGroup01, "T1") {
            @Override
            public void run() {
                try {
                    System.out.println(getThreadGroup().getName());
                    System.out.println(getThreadGroup().getParent());
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();


        ThreadGroup threadGroup02 = new ThreadGroup(threadGroup01, "TG2");
        System.out.println("新建线程组:" + threadGroup02.getName());
        System.out.println("获取新线程组的父线程组:" + threadGroup02.getParent().getName());
/*        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getName());*/

    }
}
