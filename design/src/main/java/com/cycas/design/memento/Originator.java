package com.cycas.design.memento;

/**
 * 发起人
 * @author xin.na
 * @since 2024/5/14 15:31
 */
public class Originator {

    // 状态
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 显示数据
    public void show() {
        System.out.println("State:" + state);
    }

    // 创建备忘录
    public Memento createMemento() {
        return new Memento(this.state);
    }

    // 恢复备忘录
    public void recoveryMemento(Memento memento) {
        this.setState(memento.getState());
    }
}
