package com.hqy.concurrency06;

import java.util.function.Consumer;

public class FutureService {
    /**
     * 定义一个submit方法
     * @param task
     * @param <T>
     * @return
     */
    public <T> Future<T> submit(final FutureTask<T> task) {
        //新建一个票据实例
        AsynFuture<T> asynFuture = new AsynFuture<>();
        //新建一个线程,
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
        }).start();
        //返回票据
        return asynFuture;
    }

        public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
        //新建一个票据实例
        AsynFuture<T> asynFuture = new AsynFuture<>();
        //新建一个线程,
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);
        }).start();
        //返回票据
        return asynFuture;
    }


}
