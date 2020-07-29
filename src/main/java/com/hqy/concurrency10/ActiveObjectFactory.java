package com.hqy.concurrency10;

import com.hqy.concurrency10.activeObjectInterface.ActiveObject;

public class ActiveObjectFactory {

    private ActiveObjectFactory() {

    }

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(activationQueue);

        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThread, servant);
        schedulerThread.start();
        return proxy;
    }

}
