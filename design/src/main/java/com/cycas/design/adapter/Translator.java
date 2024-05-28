package com.cycas.design.adapter;

/**
 * @author xin.na
 * @since 2024/5/14 14:06
 */
public class Translator extends Player {

    private ForeignCenter foreignCenter = new ForeignCenter();

    public Translator(String name) {
        super(name);
        foreignCenter.setName(name);
    }

    @Override
    void attack() {
        foreignCenter.jinGong();
    }

    @Override
    void defense() {
        foreignCenter.fangShou();
    }
}
