package com.cycas.design.interpreter;

/**
 * 音节表达式
 * @author xin.na
 * @since 2024/5/23 15:56
 */
public class ScaleExpression extends Expression {

    @Override
    public void execute(String key, double value) {
        String scale = "";
        switch ((int) value) {
            case 1:
                scale = "低音";
                break;
            case 2:
                scale = "中音";
                break;
            case 3:
                scale = "高音";
                break;
        }
        System.out.print(scale + " ");
    }
}
