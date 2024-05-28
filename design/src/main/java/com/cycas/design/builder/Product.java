package com.cycas.design.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品类
 * @author xin.na
 * @since 2024/5/11 13:42
 */
public class Product {

    List<String> parts = new ArrayList<>();

    /**
     * 添加新的产品部件
     * @param part
     */
    public void add(String part) {
        parts.add(part);
    }

    /**
     * 列举所有产品部件
     */
    public void show() {
        for (String part : parts) {
            System.out.println(part);
        }
    }
}
