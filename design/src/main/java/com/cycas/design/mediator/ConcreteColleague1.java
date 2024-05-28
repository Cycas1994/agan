package com.cycas.design.mediator;

/**
 * 具体同事类
 * @author xin.na
 * @since 2024/5/22 9:54
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        super.mediator.send(message, this);
    }

    public void receive(String message) {
        System.out.println("同事1收到消息:" + message);
    }
}
