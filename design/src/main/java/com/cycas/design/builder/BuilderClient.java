package com.cycas.design.builder;

/**
 * 客户端
 * @author xin.na
 * @since 2024/5/11 13:51
 */
public class BuilderClient {

    public static void main(String[] args) {
        Director director = new Director();
        ConcreteBuilder1 builder1 = new ConcreteBuilder1();
        ConcreteBuilder2 builder2 = new ConcreteBuilder2();

        director.construct(builder1);
        Product product1 = builder1.getResult();
        product1.show();

        director.construct(builder2);
        Product product2 = builder2.getResult();
        product2.show();
    }
}
