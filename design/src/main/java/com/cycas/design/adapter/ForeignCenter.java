package com.cycas.design.adapter;

/**
 * 外籍中锋
 * @author xin.na
 * @since 2024/5/14 14:05
 */
public class ForeignCenter {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void jinGong() {
        System.out.println("外籍中锋" + this.name + "进攻");
    }

    public void fangShou() {
        System.out.println("外籍中锋" + this.name + "防守");
    }
}
