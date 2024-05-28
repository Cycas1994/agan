package com.cycas.design.chain;

/**
 * 具体处理者
 * @author xin.na
 * @since 2024/5/21 16:47
 */
public class ConcreteHandler3 extends Handler {

    @Override
    public void handleRequest(int request) {
        if (request >= 20 && request < 30) {
            System.out.println(this.getClass().getSimpleName() + " 处理请求 " + request);
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
