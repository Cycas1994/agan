package com.cycas.design.composite;

/**
 * @author xin.na
 * @since 2024/5/15 14:47
 */
public class ComponentClient {

    public static void main(String[] args) {
        Component root = new Composite("root");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        Component comp = new Composite("Composite X");
        comp.add(new Leaf("Leaf XA"));
        comp.add(new Leaf("Leaf XB"));
        root.add(comp);

        Component comp2 = new Composite("Composite XY");
        comp2.add(new Leaf("Leaf XYA"));
        comp2.add(new Leaf("Leaf XYB"));
        comp.add(comp2);

        Leaf leafC = new Leaf("Leaf C");
        root.add(leafC);

        Leaf leafD = new Leaf("Leaf D");
        root.add(leafD);
        root.remove(leafD);

        root.display(1);

    }
}
