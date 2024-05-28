package com.cycas.design.observer;

/**
 * 看NBA同事类
 * @author xin.na
 * @since 2024/5/11 16:32
 */
public class NBAObserver extends Observer {

    public NBAObserver(String name, Subject sub) {
        super(name, sub);
    }

    @Override
    void update() {
        System.out.println(sub.getName() + ":" + sub.getAction() + "," + super.name + ",请停止观察NBA直播,赶紧工作");
    }
}
