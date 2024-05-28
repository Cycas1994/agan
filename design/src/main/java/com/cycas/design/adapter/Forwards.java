package com.cycas.design.adapter;

/**
 * 前锋
 * @author xin.na
 * @since 2024/5/14 11:25
 */
public class Forwards extends Player {


    public Forwards(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println("前锋" + super.name + "进攻");
    }

    @Override
    void defense() {
        System.out.println("前锋" + super.name + "防守");
    }
}
