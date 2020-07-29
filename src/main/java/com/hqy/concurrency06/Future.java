package com.hqy.concurrency06;

public interface Future<T> {
    T get() throws InterruptedException;
}
