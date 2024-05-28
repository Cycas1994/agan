package com.cycas.design.interpreter;

/**
 * 包含解释器之外的全局信息
 * @author xin.na
 * @since 2024/5/22 16:06
 */
public class Context {

    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    private String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
