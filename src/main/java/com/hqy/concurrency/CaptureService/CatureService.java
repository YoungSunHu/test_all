package com.hqy.concurrency.CaptureService;

import java.util.*;

public class CatureService {

    private final static LinkedList<Control> CONTROLS = new LinkedList<>();
    private final static int MAX_WORKERS = 5;

    public static void main(String[] args) {
        List<Thread> workers = new ArrayList<>();
        //创建线程
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", ",M7", "M8", "M9", "M10").stream().map(CatureService::createThread).forEach(t -> {
            t.start();
            workers.add(t);
        });

        workers.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All  capture work finished").ifPresent(System.out::println);
    }


    public static Thread createThread(String threadName) {
        return new Thread(
                () -> {
                    Optional.of("The worker [" + Thread.currentThread().getName() + "] begin capture data").ifPresent(System.out::println);
                    synchronized (CONTROLS) {
                        while (CONTROLS.size() > MAX_WORKERS) {
                            try {
                                CONTROLS.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        CONTROLS.addLast(new Control());
                    }

                    Optional.of("The worker [" + Thread.currentThread().getName() + "] is woking....").ifPresent(System.out::println);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (CONTROLS) {
                        Optional.of("The worker [" + Thread.currentThread().getName() + "] END.").ifPresent(System.out::println);
                        CONTROLS.removeFirst();
                        CONTROLS.notifyAll();
                    }
                }
                , threadName
        );
    }


    private static class Control {

    }
}
