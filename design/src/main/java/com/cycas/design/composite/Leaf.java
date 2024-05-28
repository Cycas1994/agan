package com.cycas.design.composite;

/**
 * @author xin.na
 * @since 2024/5/15 14:29
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    void add(Component component) {
        System.out.println("Cannot add to a leaf.");
    }

    @Override
    void remove(Component component) {
        System.out.println("Cannot remove from a leaf.");
    }

    @Override
    void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);
    }
}
