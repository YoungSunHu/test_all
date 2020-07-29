package com.hqy.concurrency_TreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSimulator<T> {

    //使用Map存储线程
    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            storage.put(Thread.currentThread(), t);
        }
    }

    //key始终为当前线程
    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (value == null) {
                return initvalue();
            }
            return value;
        }
    }

    public T initvalue() {
        return null;
    }
}
