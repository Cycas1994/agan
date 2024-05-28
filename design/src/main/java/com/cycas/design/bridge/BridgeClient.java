package com.cycas.design.bridge;

/**
 * @author xin.na
 * @since 2024/5/17 15:16
 */
public class BridgeClient {

    public static void main(String[] args) {
        Abstraction ab = new RefinedAbstraction();
        ab.setImplementor(new ConcreteImplementorA());
        ab.operation();

        ab.setImplementor(new ConcreteImplementorB());
        ab.operation();
    }
}
