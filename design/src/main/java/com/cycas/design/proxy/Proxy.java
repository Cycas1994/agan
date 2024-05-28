package com.cycas.design.proxy;

/**
 * @author xin.na
 * @since 2024/5/28 9:35
 */
public class Proxy implements ISubject {

    private RealSubject rs;

    public Proxy() {
        rs = new RealSubject();
    }

    @Override
    public void request() {
        rs.request();
    }
}
