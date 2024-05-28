package com.cycas.design.composite;

/**
 * 财务部 树叶节点
 * @author xin.na
 * @since 2024/5/15 16:25
 */
public class FinanceDepartment extends Company {

    public FinanceDepartment(String name) {
        super(name);
    }

    @Override
    void add(Company company) {

    }

    @Override
    void remove(Company company) {

    }

    @Override
    void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);
    }

    @Override
    void lineOfDuty() {
        System.out.println(name + " 公司财务收支管理");
    }
}
