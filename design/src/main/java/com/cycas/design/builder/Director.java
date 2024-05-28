package com.cycas.design.builder;

/**
 * 指挥者
 * @author xin.na
 * @since 2024/5/11 13:50
 */
public class Director {

    public void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}
