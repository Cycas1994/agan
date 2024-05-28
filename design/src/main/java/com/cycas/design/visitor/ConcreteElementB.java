package com.cycas.design.visitor;

/**
 * @author xin.na
 * @since 2024/5/24 13:54
 */
public class ConcreteElementB extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }
}
