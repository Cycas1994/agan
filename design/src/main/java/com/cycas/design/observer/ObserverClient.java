package com.cycas.design.observer;

/**
 * @author xin.na
 * @since 2024/5/11 15:21
 */
public class ObserverClient {

    public static void main(String[] args) {
        // 老板胡汉三
        Subject boss1 = new BossSubject("胡汉三");

        // 看股票的同事
        Observer employee1 = new StockObserver("魏关姹", boss1);
        Observer employee2 = new StockObserver("易管查", boss1);
        // 看NBA的同事
        Observer employee3 = new NBAObserver("兰秋幂", boss1);

        // 老板登记下三个同事
        boss1.attach(employee1);
        boss1.attach(employee2);
        boss1.attach(employee3);

        // 老板回来了
        boss1.setAction("我胡汉三回来了");
        // 通知同事
        boss1.notifyObserver();

    }
}
