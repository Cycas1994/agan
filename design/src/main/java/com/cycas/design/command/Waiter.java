package com.cycas.design.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务员类
 * @author xin.na
 * @since 2024/5/17 16:04
 */
public class Waiter {

    private List<Command> orders = new ArrayList<>();

    public void setOrder(Command command) {
        String className = command.getClass().getSimpleName();
        if (className.equals("BakeChickenWingCommand")) {
            System.out.println("服务员：鸡翅没有了，请点别的烧烤。");
        } else {
            this.orders.add(command);
            System.out.println("增加菜单：" + className + " 时间：" + this.getNowTime());
        }
    }

    public void cancelOrder(Command command) {
        String className = command.getClass().getSimpleName();
        orders.remove(command);
        System.out.println("取消订单：" + className + " 时间：" + this.getNowTime());
    }

    public void notifyCommand() {
        for (Command command : orders) {
            command.executeCommand();
        }
    }

    private String getNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }
}
