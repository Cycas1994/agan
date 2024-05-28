package com.cycas.design.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元工厂
 * @author xin.na
 * @since 2024/5/22 14:30
 */
public class FlyWeightFactory {

    private Map<String, FlyWeight> flyWeights = new ConcurrentHashMap<String, FlyWeight>();

    public FlyWeightFactory() {
        flyWeights.put("X", new ConcreteFlyWeight());
        flyWeights.put("Y", new ConcreteFlyWeight());
        flyWeights.put("Z", new ConcreteFlyWeight());
    }

    public FlyWeight getFlyWeight(String key) {
        return flyWeights.get(key);
    }
}
