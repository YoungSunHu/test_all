package com.hqy.concurrency10;

import com.hqy.concurrency10.activeObjectInterface.ActiveObject;
import com.hqy.concurrency10.activeObjectInterface.Result;

/**
 * 包可见,部允许实例化
 */
class ActiveObjectProxy implements ActiveObject {

    private final SchedulerThread schedulerThread;

    private final Servant servant;

    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillChar) {
        FutrueResult futrueResult = new FutrueResult();
        schedulerThread.invoke(new MakeStringRequest(servant, futrueResult, count, fillChar));
        return futrueResult;
    }

    @Override
    public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant, text));
    }
}
