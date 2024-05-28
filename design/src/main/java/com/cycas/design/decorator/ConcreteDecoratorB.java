package com.cycas.design.decorator;

/**
 * @author xin.na
 * @since 2024/5/27 19:27
 */
public class ConcreteDecoratorB extends Decorator {

    @Override
    public void operation() {
        super.operation();
        this.addedBehavior();
    }

    private void addedBehavior() {
        System.out.println("具体装饰对象B的独有操作");
    }
}
