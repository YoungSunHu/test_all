package com.hqy.concurrency.simpleThreadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool extends Thread {

    //线程池大小
    public int SIZE;
    //队列大小
    public final int QUEUESIZE;
    //线程池初始化大小
    public final static int DEFAULT_SIZE = 10;
    //队列初始化大小
    public final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    //序列
    private static volatile int seq = 0;
    private final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private final static ThreadGroup GROUP = new ThreadGroup("pool_group");
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    private final static List<WorkTask> THREAD_QUEUE = new ArrayList<>();
    private final DiscardPolicy discardPolicy;
    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This Task.");
    };
    private volatile boolean destroy = false;
    private int min;
    private int max;
    private int active;

    public SimpleThreadPool() {
        this(DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int size, int queueSize, DiscardPolicy discardPolicy) {
        this.SIZE = size;
        this.QUEUESIZE = queueSize;
        this.discardPolicy = discardPolicy;
        //初始化
        init();
    }

    public void init() {
        for (int i = 0; i < this.min; i++) {
            creatWorkTask();
        }
        this.SIZE = min;
    }

    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("The thread pool already destroy and not allowed submit task ");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > QUEUESIZE) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void run() {
        while (!destroy) {

        }
    }

    public void creatWorkTask() {
        WorkTask task = new WorkTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }


    private enum TaskSate {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }

        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkTask task : THREAD_QUEUE) {
                if (task.getTaskSate() == TaskSate.BLOCKED) {
                    task.interrupt();
                    task.colse();
                    initVal--;
                } else {
                    Thread.sleep(10);
                }
            }
            this.destroy = true;
            System.out.println("The Thread disposed.");
        }
    }

    public int getSIZE() {
        return SIZE;
    }

    public int getQUEUESIZE() {
        return QUEUESIZE;
    }

    public boolean isDestroy() {
        return destroy;
    }


    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }


    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    private static class WorkTask extends Thread {
        private volatile TaskSate taskSate = TaskSate.FREE;

        public WorkTask(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        public TaskSate getTaskSate() {
            return this.taskSate;
        }

        @Override
        public void run() {
            //lable
            OUTER:
            while (this.taskSate != TaskSate.DEAD) {
                Runnable runnable = null;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskSate = TaskSate.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            //break到OUTER去
                            break OUTER;
                        }
                        //任务队列拿出第一个给到runnable
                        runnable = TASK_QUEUE.removeFirst();
                    }
                    if (runnable != null) {
                        taskSate = TaskSate.RUNNING;
                        runnable.run();
                        taskSate = TaskSate.FREE;
                    }
                }
            }
        }

        public void colse() {
            this.taskSate = TaskSate.DEAD;
        }
    }


    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(6, 10, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        for (int i = 0; i < 40; i++) {
            simpleThreadPool.submit(() -> {
                System.out.println("The runnable   be serviced by " + Thread.currentThread());
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable  be serviced by " + Thread.currentThread() + "finished.");
            });
            System.out.println("================================================");
        }
    }
}
