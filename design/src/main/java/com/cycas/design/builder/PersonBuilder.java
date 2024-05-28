package com.cycas.design.builder;

import java.awt.*;

/**
 * 抽象的建造者类
 * @author xin.na
 * @since 2024/5/10 16:30
 */
public abstract class PersonBuilder {

    protected Graphics g;

    public PersonBuilder(Graphics g) {
        this.g = g;
    }

    abstract void buildHead();
    abstract void buildBody();
    abstract void buildArmLeft();
    abstract void buildArmRight();
    abstract void buildLegLeft();
    abstract void buildLegRight();
}
