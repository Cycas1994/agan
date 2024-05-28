package com.cycas.design.mediator;

/**
 * 抽象同事类
 * @author xin.na
 * @since 2024/5/22 9:52
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
