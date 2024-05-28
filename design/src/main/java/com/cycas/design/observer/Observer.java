package com.cycas.design.observer;

/**
 * 抽象观察者
 * @author xin.na
 * @since 2024/5/11 16:29
 */
public abstract class Observer {
    protected String name;
    protected Subject sub;
    public Observer(String name, Subject sub) {
        this.name = name;
        this.sub = sub;
    }
    abstract void update();
}
