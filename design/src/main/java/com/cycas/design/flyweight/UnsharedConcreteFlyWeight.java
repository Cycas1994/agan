package com.cycas.design.flyweight;

/**
 * 不需要共享的具体享元类
 * @author xin.na
 * @since 2024/5/22 14:27
 */
public class UnsharedConcreteFlyWeight extends FlyWeight {

    @Override
    public void operation(int extrinsicState) {
        System.out.println("不共享的具体FlyWeight:" + extrinsicState);
    }
}
