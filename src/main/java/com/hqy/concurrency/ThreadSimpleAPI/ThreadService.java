package com.hqy.concurrency.ThreadSimpleAPI;

public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;


    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                super.run();
                //创建一个守护线程
                Thread daemoonThread = new Thread(task);
                daemoonThread.setDaemon(true);

                daemoonThread.start();
                try {
                    daemoonThread.join();
                    finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - currentTime >= mills) {
                System.out.println("任务超时!");
                executeThread.interrupt();
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断!");
                break;
            }
        }
    }


}
