package com.cycas.design.iterator;

/**
 * 具体迭代器类 倒序
 * @author xin.na
 * @since 2024/5/17 10:28
 */
public class ConcreteIteratorDesc {

    private ConcreteAggregate aggregate;
    private int current = 0;

    public ConcreteIteratorDesc(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
        current = aggregate.getCount() - 1;
    }

    public Object first() {
        return aggregate.getCurrentItem(aggregate.getCount() - 1);
    }

    public Object next() {
        Object ret = null;
        current--;
        if (current >= 0) {
            ret = aggregate.getCurrentItem(current);
        }
        return ret;
    }

    public boolean isDone() {
        return current < 0;
    }

    public Object currentItem() {
        return aggregate.getCurrentItem(current);
    }
}
