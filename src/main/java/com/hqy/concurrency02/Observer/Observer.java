package com.hqy.concurrency02.Observer;

public abstract class Observer {
    //需要观察的对象作为成员变量
    protected Subject subject;

    //在构造器中加入需要观察的实例对象
    public Observer(Subject subject) {
        this.subject = subject;
        //在实例对象中加入自己,以便subject发生改变时能通知自己
        this.subject.attach(this);
    }

    public abstract void update();
}
