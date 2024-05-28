package com.cycas.design.interpreter;

/**
 * 抽象表达式
 * @author xin.na
 * @since 2024/5/23 15:43
 */
public abstract class Expression {

     public void interpret(PlayContext context) {
         if (context.getPlayText().length() == 0) {
             return;
         }
         String playKey = context.getPlayText().substring(0, 1);
         context.setPlayText(context.getPlayText().substring(2));
         double playValue = Double.parseDouble(context.getPlayText().substring(0, context.getPlayText().indexOf(" ")));
         context.setPlayText(context.getPlayText().substring(context.getPlayText().indexOf(" ") + 1));
         this.execute(playKey, playValue);
     }

     public abstract void execute(String key, double value);
}
