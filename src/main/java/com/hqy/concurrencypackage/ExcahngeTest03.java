package com.hqy.concurrencypackage;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ExcahngeTest03 {

    public static void main(String[] args) {

        final Exchanger<Integer> exchanger = new Exchanger<Integer>();

        new Thread() {
            @Override
            public void run() {
                //Object aobj = new Object();
                AtomicReference<Integer> value = new AtomicReference<Integer>(1);
                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread A has Value:" + value.get());
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                //Object aobj = new Object();
                AtomicReference<Integer> value = new AtomicReference<Integer>(2);
                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread B has Value:" + value.get());
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


}
