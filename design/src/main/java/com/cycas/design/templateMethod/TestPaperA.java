package com.cycas.design.templateMethod;

/**
 * @author xin.na
 * @since 2024/5/9 19:17
 */
public class TestPaperA extends TestPaper {

    @Override
    protected String answer1() {
        return "b";
    }

    @Override
    protected String answer2() {
        return "a";
    }
}
