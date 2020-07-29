package com.hqy.concurrency08;

public class QueryFromDBAction {

    public void execute(Context context) {
        try {
            Thread.sleep(1000L);
            String name = "Alex";
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
