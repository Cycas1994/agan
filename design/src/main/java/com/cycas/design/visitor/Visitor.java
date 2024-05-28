package com.cycas.design.visitor;

/**
 * 抽象的访问者
 * @author xin.na
 * @since 2024/5/24 13:49
 */
public abstract class Visitor {

    public abstract void visitConcreteElementA(ConcreteElementA concreteElementA);

    public abstract void visitConcreteElementB(ConcreteElementB concreteElementB);
}
