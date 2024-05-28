package com.cycas.design.composite;

/**
 * @author xin.na
 * @since 2024/5/15 16:04
 */
public abstract class Company {

    protected String name;

    public Company(String name) {
        this.name = name;
    }

    abstract void add(Company company);
    abstract void remove(Company company);
    abstract void display(int depth);
    abstract void lineOfDuty();
}
