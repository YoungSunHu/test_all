package com.hqy.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest05 {

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

/*        final Phaser phaser = new Phaser(3);

        new Thread(phaser::arrive).start();
        TimeUnit.SECONDS.sleep(5);*/
        final Phaser phaser = new Phaser(5);
        for (int i = 0; i < 4; i++) {
            new ArriveTask(phaser, i).start();
        }

        phaser.arriveAndAwaitAdvance();

        System.out.println("The phaser work finished done;");
    }

    private static class ArriveTask extends Thread {

        private final Phaser phaser;

        private ArriveTask(Phaser phaser, int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " start working.");
            sleepSeconds();
            System.out.println(getName() + " The phase one is running.");
            //arrive()不会被block住
            phaser.arrive();

            sleepSeconds();

            System.out.println(getName() + " keep doing other thing.");
        }
    }

    private static void sleepSeconds() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
