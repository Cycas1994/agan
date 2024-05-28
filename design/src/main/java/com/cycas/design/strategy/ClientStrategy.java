package com.cycas.design.strategy;

/**
 * @author xin.na
 * @since 2024/5/28 14:26
 */
public class ClientStrategy {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyA());
        context.contextInterface();
    }
}
