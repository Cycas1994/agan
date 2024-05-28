package com.cycas.design.visitor;

/**
 * @author xin.na
 * @since 2024/5/24 13:53
 */
public abstract class Element {

    public abstract void accept(Visitor visitor);

}
