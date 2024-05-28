package com.cycas.design.chain;

/**
 * 具体处理者类
 * @author xin.na
 * @since 2024/5/21 16:41
 */
public class ConcreteHandler1 extends Handler {

    @Override
    public void handleRequest(int request) {
        if (request >= 0 && request < 10) {
            System.out.println(this.getClass().getSimpleName() + " 处理请求 " + request);
        } else if (super.successor != null) {
            super.successor.handleRequest(request);
        }
    }
}
