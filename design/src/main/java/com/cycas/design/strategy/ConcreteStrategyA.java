package com.cycas.design.strategy;

/**
 * 具体算法A
 * @author xin.na
 * @since 2024/5/28 14:22
 */
public class ConcreteStrategyA extends Strategy {

    @Override
    public void algorithmInterface() {
        System.out.println("具体算法A");
    }
}
