package com.cycas.design.bridge;

/**
 * @author xin.na
 * @since 2024/5/17 15:13
 */
public class ConcreteImplementorB extends Implementor {

    @Override
    public void operation() {
        System.out.println("具体实现B的方法执行");
    }
}
