package com.cycas.design.bridge;

/**
 * @author xin.na
 * @since 2024/5/17 15:14
 */
public class RefinedAbstraction extends Abstraction {

    @Override
    public void operation() {
        System.out.println("具体的Abstraction");
        implementor.operation();
    }
}
