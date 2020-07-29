package com.hqy.concurrency.LOCK;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements LOCK {

    //initValue的值为false表示锁已经被拿走
    //initValue的值为true表示锁是free状态
    private boolean initValue;
    private Collection<Thread> blockThreadCollection = new ArrayList<>();
    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockThreadCollection.add(Thread.currentThread());
            this.wait();
        }

        blockThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            lock();
        }
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0) throw new TimeOutException("Timeout!");
            blockThreadCollection.add(Thread.currentThread());
            this.wait();
            hasRemaining = endTime - System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + ">>" + hasRemaining);
        }
            this.initValue = true;
            this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        //判断是否为当前线程,只有调用了lock的线程才可以进行unlock
        if (Thread.currentThread() == this.currentThread) {
            this.initValue = false;
            System.out.println(Thread.currentThread() + "realse the lock monitor.");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockThreadCollection.size();
    }
}
