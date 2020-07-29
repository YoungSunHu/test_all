package com.hqy.concurrency02.Observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    //需要有一个容器,记录需要通知的Observer
    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (state == this.state) {
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer oberver) {
        observers.add(oberver);
    }

    public void notifyAllObserver() {
        //遍历通知
        observers.stream().forEach(Observer::update);
    }
}
