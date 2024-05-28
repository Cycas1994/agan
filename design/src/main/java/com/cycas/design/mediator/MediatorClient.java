package com.cycas.design.mediator;

/**
 * @author xin.na
 * @since 2024/5/22 10:03
 */
public class MediatorClient {

    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);
        mediator.setConcreteColleague1(colleague1);
        mediator.setConcreteColleague2(colleague2);
        colleague1.send("吃过饭了吗");
        colleague2.send("没有呢，你打算请客吗");
    }
}
