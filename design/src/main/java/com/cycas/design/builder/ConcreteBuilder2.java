package com.cycas.design.builder;

/**
 * 具体建造者2
 * @author xin.na
 * @since 2024/5/11 13:48
 */
public class ConcreteBuilder2 extends Builder {

    private Product product = new Product();

    @Override
    void buildPartA() {
        product.add("部件X");
    }

    @Override
    void buildPartB() {
        product.add("部件Y");
    }

    @Override
    Product getResult() {
        return product;
    }
}
