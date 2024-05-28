package com.cycas.design.interpreter;

/**
 * @author xin.na
 * @since 2024/5/23 15:57
 */
public class InterpreterClient2 {

    public static void main(String[] args) {
        PlayContext context = new PlayContext();
        System.out.println("音乐上海滩：");
        context.setPlayText("T 500 O 2 E 0.5 G 0.5 A 3 E 0.5 G 0.5 D 3 E 0.5 G 0.5 A 0.5 O 3 C 1 O 2 A 0.5 G 1 C 0.5 E 0.5 D 3 ");

        Expression expression = null;
        while (context.getPlayText().length() > 0) {
            String str = context.getPlayText().substring(0, 1);
            switch (str) {
                case "O":
                    expression = new ScaleExpression();
                    break;
                case "A":
                case "B":
                case "C":
                case "D":
                case "E":
                case "F":
                case "G":
                    expression = new NoteExpression();
                    break;
                case "T":
                    expression = new SpeedExpression();
                    break;
            }
            expression.interpret(context);
        }
    }
}
