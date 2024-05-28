package com.cycas.design.mediator;

/**
 * 具体同事类2
 * @author xin.na
 * @since 2024/5/22 9:57
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        super.mediator.send(message, this);
    }

    public void receive(String message) {
        System.out.println("同事2收到消息:" + message);
    }
}
