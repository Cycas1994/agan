package com.cycas.design.flyweight;

/**
 * 需要共享的具体享元类
 * @author xin.na
 * @since 2024/5/22 14:26
 */
public class ConcreteFlyWeight extends FlyWeight {

    @Override
    public void operation(int extrinsicState) {
        System.out.println("具体FlyWeight:" + extrinsicState);
    }
}
