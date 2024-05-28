package com.cycas.design.observer;

/**
 * 看股票同事类
 * @author xin.na
 * @since 2024/5/11 14:43
 */
public class StockObserver extends Observer {

    public StockObserver(String name, Subject sub) {
        super(name, sub);
    }

    public void update() {
        System.out.println(sub.getName() + ":" + sub.getAction() + "," + this.name + ",请停止浏览股市行情,赶紧工作");
    }


}
