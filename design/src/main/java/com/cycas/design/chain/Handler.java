package com.cycas.design.chain;

/**
 * 处理请示的接口
 * @author xin.na
 * @since 2024/5/21 16:39
 */
public abstract class Handler {

    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(int request);
}
