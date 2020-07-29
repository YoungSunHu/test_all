package com.hqy.concurrencypackage;

import java.util.Random;
import java.util.concurrent.Phaser;

public class PhaserTest {

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        final Phaser phaser = new Phaser();


    }

    static class Task extends Thread {

        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println("The Woker [" + getName() + "] is working....");
        }
    }

}
