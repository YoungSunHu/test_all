package com.hqy.concurrency.designPattern.MessagePer;

public class Message {

    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
