package com.cycas.design.iterator;

/**
 * 迭代器抽象类
 * @author xin.na
 * @since 2024/5/15 17:06
 */
public abstract class Iterator {

    abstract Object first();
    abstract Object next();
    abstract boolean isDone();
    abstract Object currentItem();
}
