package com.cycas.design.iterator;

/**
 * @author xin.na
 * @since 2024/5/15 17:21
 */
public class IteratorClient {

    public static void main(String[] args) {
        ConcreteAggregate aggregate = new ConcreteAggregate();
        aggregate.add("大鸟");
        aggregate.add("小菜");
        aggregate.add("行李");
        aggregate.add("老外");
        aggregate.add("公交内部员工");
        aggregate.add("小偷");

        Iterator iterator = new ConcreteIterator(aggregate);
        while (!iterator.isDone()) {
            System.out.println(iterator.currentItem() + " 请买车票");
            iterator.next();
        }
    }
}
