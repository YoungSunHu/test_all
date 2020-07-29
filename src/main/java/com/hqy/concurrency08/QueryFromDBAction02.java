package com.hqy.concurrency08;

public class QueryFromDBAction02 {

    public void execute() {
        try {
            Thread.sleep(1000L);
            String name = "Young";
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
