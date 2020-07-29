package com.hqy.concurrency08;

public class ExecutionTask02 implements Runnable {

    private QueryFromDBAction02 queryFromDBAction = new QueryFromDBAction02();
    private QueryFromHTTPAction02 queryFromHTTPAction = new QueryFromHTTPAction02();

    @Override
    public void run() {
        final Context context = ActionContext.getActionContext().getContext();
        queryFromDBAction.execute();
        System.out.println("The name query successful ");
        queryFromHTTPAction.execute();
        System.out.println("The card id successful ");
        System.out.println("The Name is " + context.getName() + " and CardId  " + context.getCardID());
    }
}
