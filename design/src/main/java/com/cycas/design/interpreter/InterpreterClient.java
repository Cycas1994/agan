package com.cycas.design.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/5/22 16:13
 */
public class InterpreterClient {

    public static void main(String[] args) {
        Context context = new Context();
        List<AbstractExpression> list = new ArrayList<>();
        list.add(new TerminalExpression());
        list.add(new NonTerminalExpression());
        list.add(new TerminalExpression());
        list.add(new TerminalExpression());

        for (AbstractExpression exp : list) {
            exp.interpret(context);
        }
    }
}
