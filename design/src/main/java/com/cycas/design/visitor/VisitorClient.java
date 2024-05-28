package com.cycas.design.visitor;

/**
 * @author xin.na
 * @since 2024/5/24 14:02
 */
public class VisitorClient {

    public static void main(String[] args) {
        ConcreteElementA elementA = new ConcreteElementA();
        ConcreteElementB elementB = new ConcreteElementB();
        ObjectStructure structure = new ObjectStructure();
        structure.attach(elementA);
        structure.attach(elementB);
        ConcreteVisitor1 visitor1 = new ConcreteVisitor1();
        ConcreteVisitor2 visitor2 = new ConcreteVisitor2();
        structure.visit(visitor1);
        structure.visit(visitor2);
    }
}
