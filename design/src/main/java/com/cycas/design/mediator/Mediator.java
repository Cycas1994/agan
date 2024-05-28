package com.cycas.design.mediator;

/**
 * 抽象中介者类
 * @author xin.na
 * @since 2024/5/22 9:53
 */
public abstract class Mediator {

    public abstract void send(String message, Colleague colleague);

}
