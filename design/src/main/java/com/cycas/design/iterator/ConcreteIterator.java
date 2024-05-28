package com.cycas.design.iterator;

/**
 * 具体迭代器
 * @author xin.na
 * @since 2024/5/15 17:15
 */
public class ConcreteIterator extends Iterator {

    private ConcreteAggregate aggregate;

    private int current;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    Object first() {
        return aggregate.getCurrentItem(0);
    }

    @Override
    Object next() {
        Object ret = null;
        current++;
        if (current < aggregate.getCount()) {
            ret = aggregate.getCurrentItem(current);
        }
        return ret;
    }

    @Override
    boolean isDone() {
        return current >= aggregate.getCount();
    }

    @Override
    Object currentItem() {
        return aggregate.getCurrentItem(current);
    }
}
