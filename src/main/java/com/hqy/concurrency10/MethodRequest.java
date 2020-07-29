package com.hqy.concurrency10;

/**
 * 对应ActiveObject的每一个方法
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutrueResult futrueResult;

    protected MethodRequest(Servant servant, FutrueResult futrueResult) {
        this.servant = servant;
        this.futrueResult = futrueResult;
    }

    public abstract void execute();
}
