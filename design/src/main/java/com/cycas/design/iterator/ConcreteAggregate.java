package com.cycas.design.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚集类
 * @author xin.na
 * @since 2024/5/15 17:07
 */
public class ConcreteAggregate extends Aggregate {

    // 存放聚合对象
    private List<Object> items = new ArrayList<>();

    @Override
    Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public int getCount() {
        return items.size();
    }

    public void add(Object obj) {
        items.add(obj);
    }

    public Object getCurrentItem(int index) {
        return items.get(index);
    }

}
