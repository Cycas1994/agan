package com.cycas.design.memento;

/**
 * 备忘录
 * @author xin.na
 * @since 2024/5/14 15:34
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
