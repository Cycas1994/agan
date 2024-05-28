package com.cycas.design.state;

/**
 * 下午状态
 * @author xin.na
 * @since 2024/5/14 9:47
 */
public class AfternoonState extends State {

    @Override
    void writeProgram(Work w) {
        if (w.getHour() < 17) {
            System.out.println("当前时间:" + w.getHour() + "点 下午状态还不错,继续努力");
        } else {
            // 转入旁晚状态
            w.setState(new EveningState());
            w.writeProgram();
        }
    }
}
