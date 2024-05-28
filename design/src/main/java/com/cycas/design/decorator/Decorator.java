package com.cycas.design.decorator;

/**
 * @author xin.na
 * @since 2024/5/27 19:24
 */
public class Decorator extends Component {

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}
