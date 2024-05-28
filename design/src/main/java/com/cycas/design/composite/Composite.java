package com.cycas.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/5/15 14:41
 */
public class Composite extends Component {

    private List<Component> children = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    void add(Component component) {
        children.add(component);
    }

    @Override
    void remove(Component component) {
        children.remove(component);
    }

    @Override
    void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);
        for (Component item : children) {
            item.display(depth + 2);
        }
    }
}
