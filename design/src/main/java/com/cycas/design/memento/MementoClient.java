package com.cycas.design.memento;

/**
 * @author xin.na
 * @since 2024/5/14 15:52
 */
public class MementoClient {

    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("on");
        originator.show();

        CareTaker c = new CareTaker();
        // 保存状态时 由于有了很好的封装 可以隐藏Originator实现细节
        c.setMemento(originator.createMemento());

        originator.setState("off");
        originator.show();

        originator.recoveryMemento(c.getMemento());
        originator.show();
    }
}
