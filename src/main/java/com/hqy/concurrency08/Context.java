package com.hqy.concurrency08;

public class Context {

    private String name;
    private String cardID;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCardId(String cardId) {
        this.cardID = cardId;
    }

    public String getCardID() {
        return cardID;
    }
}
