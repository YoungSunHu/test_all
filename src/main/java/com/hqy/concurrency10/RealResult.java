package com.hqy.concurrency10;

import com.hqy.concurrency10.activeObjectInterface.Result;

public class RealResult implements Result {

    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return resultValue;
    }
}
