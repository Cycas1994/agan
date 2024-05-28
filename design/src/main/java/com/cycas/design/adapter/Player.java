package com.cycas.design.adapter;

/**
 * 球员
 * @author xin.na
 * @since 2024/5/14 11:22
 */
public abstract class Player {

    protected String name;

    public Player(String name) {
        this.name = name;
    }

    abstract void attack();

    abstract void defense();
}
