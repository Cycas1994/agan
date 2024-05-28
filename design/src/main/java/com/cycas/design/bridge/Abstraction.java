package com.cycas.design.bridge;

/**
 * @author xin.na
 * @since 2024/5/17 15:13
 */
public abstract class Abstraction {
    protected Implementor implementor;

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}
