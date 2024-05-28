package com.cycas.design.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/5/24 14:00
 */
public class ObjectStructure {

    private List<Element> elements = new ArrayList<>();

    public void attach(Element element) {
        elements.add(element);
    }

    public void detach(Element element) {
        elements.remove(element);
    }

    public void visit(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}
