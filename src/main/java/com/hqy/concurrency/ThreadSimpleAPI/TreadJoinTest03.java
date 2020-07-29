package com.hqy.concurrency.ThreadSimpleAPI;

public class TreadJoinTest03 {


    public static void main(String[] args) {

        long startTimestamp = System.currentTimeMillis();
        Thread thread01 = new Thread(new CaptureRunnable("M1", 10000L));
        Thread thread02 = new Thread(new CaptureRunnable("M2", 20000L));
        Thread thread03 = new Thread(new CaptureRunnable("M3", 30000L));

        thread01.start();
        thread02.start();
        thread03.start();

        long endTimestamp = System.currentTimeMillis();
        System.out.printf("Save data begin is : [%s],end timestamp is : [%s] \n",startTimestamp,endTimestamp);
    }


}

class CaptureRunnable implements Runnable {

    private String machineName;
    private Long spendTime;

    public CaptureRunnable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spendTime);
            System.out.println("校验成功!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getResult() {
        return machineName + "finish.";
    }

}
