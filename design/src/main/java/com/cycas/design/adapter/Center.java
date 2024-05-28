package com.cycas.design.adapter;

/**
 * 中锋
 * @author xin.na
 * @since 2024/5/14 11:26
 */
public class Center extends Player {

    public Center(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println("中锋" + super.name + "进攻");
    }

    @Override
    void defense() {
        System.out.println("中锋" + super.name + "防守");
    }
}
