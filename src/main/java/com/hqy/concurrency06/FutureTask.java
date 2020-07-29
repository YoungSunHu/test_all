package com.hqy.concurrency06;

/**
 * 进行逻辑上的隔离
 * @param <T>
 */
public interface FutureTask<T> {
    T call();
}
