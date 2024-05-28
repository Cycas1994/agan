package com.cycas.design.adapter;

/**
 * @author xin.na
 * @since 2024/5/14 11:07
 */
public class Adapter extends Target {

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
