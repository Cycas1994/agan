package com.cycas.design.templateMethod;

/**
 * @author xin.na
 * @since 2024/5/9 19:18
 */
public class TemplateMethod {

    public static void main(String[] args) {
        System.out.println("学生甲的试卷:");
        TestPaperA testPaperA = new TestPaperA();
        testPaperA.question1();
        testPaperA.question2();

        System.out.println("学生乙的试卷:");
        TestPaperB testPaperB = new TestPaperB();
        testPaperB.question1();
        testPaperB.question2();
    }
}
