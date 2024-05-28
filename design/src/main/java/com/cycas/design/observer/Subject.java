package com.cycas.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象通知者
 * @author xin.na
 * @since 2024/5/11 16:40
 */
public abstract class Subject {
    protected String name;
    public Subject(String name) {
        this.name = name;
    }
    private List<Observer> list = new ArrayList<>();
    private String action;

    public void attach(Observer observer) {
        list.add(observer);
    }

    public void notifyObserver() {
        for (Observer observer : list) {
            observer.update();
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }
}
