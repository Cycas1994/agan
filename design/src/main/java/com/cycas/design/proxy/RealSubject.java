package com.cycas.design.proxy;

/**
 * @author xin.na
 * @since 2024/5/28 9:35
 */
public class RealSubject implements ISubject {

    @Override
    public void request() {
        System.out.println("真实的请求");
    }

}
