package com.cycas.design.command;

/**
 * 烤羊肉命令类
 * @author xin.na
 * @since 2024/5/17 16:02
 */
public class BakeMuttonCommand extends Command {

    public BakeMuttonCommand(Barbecuer barbecuer) {
        super(barbecuer);
    }

    @Override
    public void executeCommand() {
        super.barbecuer.bakeMutton();
    }
}
