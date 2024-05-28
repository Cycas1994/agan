package com.cycas.design.interpreter;

/**
 * 终结符表达式
 * @author xin.na
 * @since 2024/5/22 16:06
 */
public class TerminalExpression extends AbstractExpression {

    @Override
    public void interpret(Context context) {
        System.out.println("终端解释器");
    }

}
