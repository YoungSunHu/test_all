package com.hqy.concurrency06;

public class SyncInvoker {
    public static void main(String[] args) {
        /* String result = get();
        System.out.println(result);*/
        FutureService futureService = new FutureService();
        //调用futureService的submit方法并返回一个future(票据)
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        });

        System.out.println("=================================================");
        System.out.println("Do other thing.........");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String get() throws InterruptedException {
        Thread.sleep(10000);
        return "FINISH";
    }
}
