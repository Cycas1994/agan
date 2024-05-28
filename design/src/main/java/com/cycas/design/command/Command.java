package com.cycas.design.command;

/**
 * 抽象命令类
 * @author xin.na
 * @since 2024/5/17 15:59
 */
public abstract class Command {

    protected Barbecuer barbecuer;

    public Command(Barbecuer barbecuer) {
        this.barbecuer = barbecuer;
    }

    public abstract void executeCommand();
}
