package com.cycas.design.builder;

/**
 * 具体建造者1
 * @author xin.na
 * @since 2024/5/11 13:46
 */
public class ConcreteBuilder1 extends Builder {

    private Product product = new Product();

    @Override
    void buildPartA() {
        product.add("部件A");
    }

    @Override
    void buildPartB() {
        product.add("部件B");
    }

    @Override
    Product getResult() {
        return product;
    }
}
