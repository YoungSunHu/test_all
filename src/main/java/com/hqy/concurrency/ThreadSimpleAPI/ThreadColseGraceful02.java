package com.hqy.concurrency.ThreadSimpleAPI;

public class ThreadColseGraceful02 {

    private static class Worker extends Thread {
        //volatile
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("检测到线程被打断!");
                    // e.printStackTrace();
                    break;
                }
            }
        }

        public void shutdown() {
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();
    }

}
