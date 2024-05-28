package com.cycas.design.interpreter;

/**
 * 音符表达式
 * @author xin.na
 * @since 2024/5/23 15:52
 */
public class NoteExpression extends Expression {

    @Override
    public void execute(String key, double value) {
        String note = "";
        switch (key) {
            case "C":
                note = "1";
                break;
            case "D":
                note = "2";
                break;
            case "E":
                note = "3";
                break;
            case "F":
                note = "4";
                break;
            case "G":
                note = "5";
                break;
            case "A":
                note = "6";
                break;
            case "B":
                note = "7";
                break;
        }
        System.out.print(note + " ");
    }
}
