package com.hqy.concurrency.ThreadSimpleAPI;

public class ThreadCloseForce02 {

    private static class Worker extends Thread {


        public static void main(String[] args) {
            ThreadService threadService = new ThreadService();

            long startTime = System.currentTimeMillis();
            threadService.execute(() -> {
                //load a very heavey resource
                while (true) {

                }
            });

            threadService.shutdown(10000);
            long endTime = System.currentTimeMillis();

            System.out.println(endTime - startTime);

        }

    }


}
