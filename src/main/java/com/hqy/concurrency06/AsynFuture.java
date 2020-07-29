package com.hqy.concurrency06;

/**
 * Futrue类相当于票据类
 * get() 方法
 * done() 方法
 *
 * @param <T>
 */
public class AsynFuture<T> implements Future<T> {

    private volatile boolean done = false;
    private T result;

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            //如果还未完成则wait
            while (!done) {
                this.wait();
            }
        }
        return null;
    }
}
