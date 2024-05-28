package com.cycas.design.builder;

/**
 * 抽象建造者类
 * @author xin.na
 * @since 2024/5/11 13:45
 */
public abstract class Builder {

    abstract void buildPartA();

    abstract void buildPartB();

    abstract Product getResult();
}
