package com.hqy.concurrency10;

public class DisplayStringRequest extends MethodRequest {

    private final String text;

    protected DisplayStringRequest(Servant servant, String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
