package com.hqy.concurrency08;

public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();
    private QueryFromHTTPAction queryFromHTTPAction = new QueryFromHTTPAction();

    @Override
    public void run() {
        final Context context = new Context();
        queryFromDBAction.execute(context);
        System.out.println("The name query successful ");
        queryFromHTTPAction.execute(context);
        System.out.println("The card id successful ");
        System.out.println("The Name is " + context.getName()+" and CardId  "+context.getCardID());
    }
}
