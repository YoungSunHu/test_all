package com.hqy.concurrency02.Observer;

public interface LifecycleListener {
    void onEvent(ObservableRunnable.RunnableEvent event);
}
