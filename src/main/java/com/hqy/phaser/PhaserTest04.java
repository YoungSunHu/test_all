package com.hqy.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest04 {
    public static void main(String[] args) throws InterruptedException {

//        final Phaser phaser = new Phaser(1);

/*        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase());*/

/*        System.out.println(phaser.getRegisteredParties());
        phaser.register();

        System.out.println(phaser.getRegisteredParties());
        phaser.register();

        System.out.println(phaser.getRegisteredParties());
        phaser.register();*/

/*        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());*/

/*
        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());
        new Thread(phaser::arriveAndAwaitAdvance).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("===========================");
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());
*/

        final Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return true;
            }
        };

        new OnAdvanceTask("A", phaser).start();
        new OnAdvanceTask("B", phaser).start();

    }


    static class OnAdvanceTask extends Thread {

        private final String name;

        private final Phaser phaser;

        @Override
        public void run() {
            System.out.println(getName() + " I am start and the phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " I am end!");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getName().equals("A")) {
                System.out.println(getName() + " I am start and the phase " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + " I am end!");
            }

        }

        OnAdvanceTask(String name, Phaser phaser) {
            this.name = name;
            this.phaser = phaser;
        }
    }
}
