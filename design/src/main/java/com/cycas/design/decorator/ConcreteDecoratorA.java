package com.cycas.design.decorator;

/**
 * @author xin.na
 * @since 2024/5/27 19:26
 */
public class ConcreteDecoratorA extends Decorator {

    private String addedState;

    @Override
    public void operation() {
        super.operation();
        this.addedState = "具体装饰对象A的独有操作";
        System.out.println(this.addedState);
    }
}
