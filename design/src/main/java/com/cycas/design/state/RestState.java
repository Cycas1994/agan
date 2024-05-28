package com.cycas.design.state;

/**
 * 休息状态
 * @author xin.na
 * @since 2024/5/14 9:55
 */
public class RestState extends State {

    @Override
    void writeProgram(Work w) {
        System.out.println("当前时间:" + w.getHour() + "点 下班回家");
    }
}
