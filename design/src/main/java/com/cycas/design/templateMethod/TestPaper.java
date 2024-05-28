package com.cycas.design.templateMethod;

/**
 * @author xin.na
 * @since 2024/5/9 19:15
 */
public abstract class TestPaper {

    public void question1() {
        System.out.println("问题1");
        System.out.println("答案:" + this.answer1());
    }

    protected abstract String answer1();

    public void question2() {
        System.out.println("问题2");
        System.out.println("答案:" + this.answer2());
    }

    protected abstract String answer2();


}
