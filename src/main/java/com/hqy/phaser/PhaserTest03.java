package com.hqy.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest03 {

    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        final Phaser phaser = new Phaser(5);

        for (int i = 0; i < 6; i++) {

        }

    }

    static class Athletes extends Thread {

        private final int no;

        private final Phaser phaser;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            sport(phaser, ":start running.", no + "end running.");
            sport(phaser, ":start bicycle.", no + "end bicycle.");
            sport(phaser, ":start long jump .", no + "end long jump.");
        }


    }


    static class InjuredAthletes extends Thread {

        private final int no;

        private final Phaser phaser;

        InjuredAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            sport(phaser, ":start running.", no + "end running.");
            sport(phaser, ":start bicycle.", no + "end bicycle.");
            System.out.println("Oh shit,I am injured! I will be exited!");
            //发生异常或超时时调用,取消注册pahser,让phaser与其他线程继续运行
            phaser.arriveAndDeregister();
        }


    }


    private static void sport(Phaser phaser, String x, String x2) {
        System.out.println(x);
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(x2);
        phaser.arriveAndAwaitAdvance();
    }

}
