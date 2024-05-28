package com.cycas.design.adapter;

/**
 * @author xin.na
 * @since 2024/5/14 11:08
 */
public class AdapterClient {

    public static void main(String[] args) {
        Player forwards = new Forwards("巴蒂尔");
        forwards.attack();

        Player guards = new Guards("麦迪");
        guards.attack();

        Player center = new Translator("姚明");
        center.attack();
        center.defense();
    }
}
