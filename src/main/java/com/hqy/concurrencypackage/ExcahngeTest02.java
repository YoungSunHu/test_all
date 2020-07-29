package com.hqy.concurrencypackage;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExcahngeTest02 {

    public static void main(String[] args) {

        final Exchanger<Object> exchanger = new Exchanger<Object>();

        new Thread() {
            @Override
            public void run() {
                Object aobj = new Object();
                System.out.println("A will send the object " + aobj);

                try {
                    Object robj = exchanger.exchange(aobj);
                    System.out.println("A recevice the object " + robj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Object bobj = new Object();
                System.out.println("B will send the object " + bobj);

                try {
                    Object robj = exchanger.exchange(bobj);
                    System.out.println("A recevice the object " + robj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


}
