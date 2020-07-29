package com.hqy.concurrency.ThreadSimpleAPI;

public class ThreadCloseForce {

    private static class Worker extends Thread {

        private boolean flagg = true;

        @Override
        public void run() {
            super.run();
            while (true) {

            }
        }

        public static void main(String[] args) {
            Worker worker = new Worker();
            worker.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
