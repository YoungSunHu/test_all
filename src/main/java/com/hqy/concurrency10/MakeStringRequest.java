package com.hqy.concurrency10;

import com.hqy.concurrency10.activeObjectInterface.Result;

/**
 * {@link com.hqy.concurrency10.activeObjectInterface.ActiveObject#makeString(int, char)}
 */
public class MakeStringRequest extends MethodRequest {

    private final int count;

    private final char fillChar;

    protected MakeStringRequest(Servant servant, FutrueResult futrueResult, int count, char fillChar) {
        super(servant, futrueResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, fillChar);
        futrueResult.setResult(result);
    }
}
