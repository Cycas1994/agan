package com.cycas.design.command;

/**
 * 烤鸡翅命令类
 * @author xin.na
 * @since 2024/5/17 16:03
 */
public class BakeChickenWingCommand extends Command {

    public BakeChickenWingCommand(Barbecuer barbecuer) {
        super(barbecuer);
    }

    @Override
    public void executeCommand() {
        super.barbecuer.bakeChickenWing();
    }
}
