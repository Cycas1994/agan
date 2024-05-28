package com.cycas.design.visitor;

/**
 * @author xin.na
 * @since 2024/5/24 13:53
 */
public class ConcreteElementA extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }

}
