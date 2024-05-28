package com.cycas.design.state;

/**
 * 上午状态
 * @author xin.na
 * @since 2024/5/14 9:40
 */
public class ForenoonState extends State {

    @Override
    void writeProgram(Work w) {
        if (w.getHour() < 12) {
            System.out.println("当前时间:" + w.getHour() + "点 上午工作,精神百倍");
        } else {
            // 超过12点 转入中午状态
            w.setState(new NoonState());
            w.writeProgram();
        }
    }
}
