package com.cycas.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体分公司类 树枝节点
 * @author xin.na
 * @since 2024/5/15 16:08
 */
public class ConcreteCompany extends Company {

    private List<Company> children = new ArrayList<>();

    public ConcreteCompany(String name) {
        super(name);
    }

    @Override
    void add(Company company) {
        children.add(company);
    }

    @Override
    void remove(Company company) {
        children.remove(company);
    }

    @Override
    void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);
        for (Company item : children) {
            item.display(depth + 2);
        }
    }

    @Override
    void lineOfDuty() {
        for (Company item : children) {
            item.lineOfDuty();
        }
    }
}
