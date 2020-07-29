package com.hqy.concurrency08;

public class QueryFromHTTPAction {

    public void execute(Context context) {
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "12345678xxxxxxx "+Thread.currentThread().getId();
    }
}
