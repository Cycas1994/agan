package com.cycas.design.flyweight;

/**
 * @author xin.na
 * @since 2024/5/22 14:32
 */
public class FlyWeightClient {

    public static void main(String[] args) {
        int extrinsicState = 22;
        FlyWeightFactory factory = new FlyWeightFactory();
        FlyWeight fx = factory.getFlyWeight("X");
        fx.operation(--extrinsicState);
        FlyWeight fy = factory.getFlyWeight("Y");
        fy.operation(--extrinsicState);
        FlyWeight fz = factory.getFlyWeight("Z");
        fz.operation(--extrinsicState);

        FlyWeight uf = new UnsharedConcreteFlyWeight();
        uf.operation(--extrinsicState);
    }
}
