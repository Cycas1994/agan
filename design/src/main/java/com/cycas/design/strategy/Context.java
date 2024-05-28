package com.cycas.design.strategy;

/**
 * 策略上下文
 * @author xin.na
 * @since 2024/5/28 14:22
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface() {
        this.strategy.algorithmInterface();
    }
}
