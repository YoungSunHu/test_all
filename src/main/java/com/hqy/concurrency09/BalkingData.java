package com.hqy.concurrency09;

public class BalkingData {
    private final String fileName;
    private final String content;
    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }
}
