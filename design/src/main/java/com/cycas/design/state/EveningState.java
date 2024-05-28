package com.cycas.design.state;

/**
 * 旁晚状态
 * @author xin.na
 * @since 2024/5/14 9:49
 */
public class EveningState extends State {

    @Override
    void writeProgram(Work w) {
        if (w.getWorkFinish()) {
            // 转入下班状态
            w.setState(new RestState());
            w.writeProgram();
        } else {
            if (w.getHour() < 21) {
                System.out.println("当前时间:" + w.getHour() + "点 加班哦,疲累之极");
            } else {
                // 转入睡眠状态
                w.setState(new SleepState());
                w.writeProgram();
            }
        }
    }
}
