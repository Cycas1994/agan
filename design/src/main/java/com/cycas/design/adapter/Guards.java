package com.cycas.design.adapter;

/**
 * 后卫
 * @author xin.na
 * @since 2024/5/14 11:27
 */
public class Guards extends Player {

    public Guards(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println("后卫" + super.name + "进攻");
    }

    @Override
    void defense() {
        System.out.println("后卫" + super.name + "进攻");
    }
}
