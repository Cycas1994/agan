package com.cycas.design.decorator;

/**
 * @author xin.na
 * @since 2024/5/27 19:24
 */
public class ConcreteComponent extends Component {

    @Override
    public void operation() {
        System.out.println("具体对象的实际操作");
    }
}
