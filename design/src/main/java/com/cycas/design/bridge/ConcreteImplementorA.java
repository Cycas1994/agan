package com.cycas.design.bridge;

/**
 * @author xin.na
 * @since 2024/5/17 15:12
 */
public class ConcreteImplementorA extends Implementor {

    @Override
    public void operation() {
        System.out.println("具体实现A的方法执行");
    }
}
