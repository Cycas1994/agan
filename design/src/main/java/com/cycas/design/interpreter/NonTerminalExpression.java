package com.cycas.design.interpreter;

/**
 * 非终结符表达式
 * @author xin.na
 * @since 2024/5/22 16:08
 */
public class NonTerminalExpression extends AbstractExpression {

    @Override
    public void interpret(Context context) {
        System.out.println("非终端解释器");
    }

}
