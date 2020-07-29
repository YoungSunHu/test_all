package com.hqy.concurrencypackage;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExcahngeTest {

    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<String>();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " start.");
                        try {
                            String result = exchanger.exchange("I am come from T-A", 10, TimeUnit.SECONDS);
                            System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " end.");
                    }
                }
                , "==A==").start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " start.");
                        try {
                            TimeUnit.SECONDS.sleep(20);
                            String result = exchanger.exchange("I am come from T-B");
                            System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " end.");
                    }
                }
                , "==B==").start();


    }


}
