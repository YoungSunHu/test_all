package com.hqy.concurrencypackage;

import java.util.concurrent.Semaphore;

public class MySemaphore extends Semaphore {

    public MySemaphore(int permits) {
        super(permits);
    }

    public MySemaphore(int permits, boolean fair) {
        super(permits, fair);
    }
}
