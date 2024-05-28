package com.cycas.design.state;

/**
 * 中午状态
 * @author xin.na
 * @since 2024/5/14 9:44
 */
public class NoonState extends State {

    @Override
    void writeProgram(Work w) {
        if (w.getHour() < 13) {
            System.out.println("当前时间:" + w.getHour() + "点 饿了,午饭; 犯困,午休");
        } else {
            // 转入下午状态
            w.setState(new AfternoonState());
            w.writeProgram();
        }
    }
}
