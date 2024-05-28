package com.cycas.design.composite;

/**
 * @author xin.na
 * @since 2024/5/15 14:28
 */
public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    abstract void add(Component component);

    abstract void remove(Component component);

    abstract void display(int depth);
}
