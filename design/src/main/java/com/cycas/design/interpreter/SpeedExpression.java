package com.cycas.design.interpreter;

/**
 * 音速表达式
 * @author xin.na
 * @since 2024/5/23 16:07
 */
public class SpeedExpression extends Expression {

    @Override
    public void execute(String key, double value) {
        String speed;
        if (value < 500) {
            speed = "慢速";
        } else if (value >= 1000) {
            speed = "快速";
        } else {
            speed = "中速";
        }
        System.out.print(speed + " ");
    }
}
