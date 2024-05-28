package com.cycas.design.mediator;

/**
 * 具体中介者类
 * @author xin.na
 * @since 2024/5/22 9:59
 */
public class ConcreteMediator extends Mediator {

    private ConcreteColleague1 concreteColleague1;
    private ConcreteColleague2 concreteColleague2;

    public void setConcreteColleague1(ConcreteColleague1 concreteColleague1) {
        this.concreteColleague1 = concreteColleague1;
    }

    public void setConcreteColleague2(ConcreteColleague2 concreteColleague2) {
        this.concreteColleague2 = concreteColleague2;
    }

    @Override
    public void send(String message, Colleague colleague) {
        if (colleague == concreteColleague1) {
            concreteColleague2.receive(message);
        } else {
            concreteColleague1.receive(message);
        }
    }
}
