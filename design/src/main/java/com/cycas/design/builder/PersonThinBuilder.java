package com.cycas.design.builder;

import java.awt.*;

/**
 * 瘦小人建造者
 * @author xin.na
 * @since 2024/5/10 16:33
 */
public class PersonThinBuilder extends PersonBuilder {

    public PersonThinBuilder(Graphics g) {
        super(g);
    }

    @Override
    void buildHead() {
        g.drawOval(150, 120, 30, 30);
    }

    @Override
    void buildBody() {
        g.drawRect(160, 150, 10, 50);
    }

    @Override
    void buildArmLeft() {
        g.drawLine(160, 150, 140, 200);
    }

    @Override
    void buildArmRight() {
        g.drawLine(170, 150, 190, 200);
    }

    @Override
    void buildLegLeft() {
        g.drawLine(160, 200, 145, 250);
    }

    @Override
    void buildLegRight() {
        g.drawLine(170, 200, 185, 250);
    }
}
