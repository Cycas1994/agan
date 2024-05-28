package com.cycas.design.state;

/**
 * 睡眠状态
 * @author xin.na
 * @since 2024/5/14 9:56
 */
public class SleepState extends State {

    @Override
    void writeProgram(Work w) {
        System.out.println("当前时间:" + w.getHour() + "点 不行了,睡着了");
    }
}
