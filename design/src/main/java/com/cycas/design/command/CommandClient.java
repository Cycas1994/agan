package com.cycas.design.command;

/**
 * @author xin.na
 * @since 2024/5/17 16:06
 */
public class CommandClient {

    public static void main(String[] args) {
        // 开店前准备
        Barbecuer barbecuer = new Barbecuer(); // 烤肉厨师
        BakeMuttonCommand bakeMuttonCommand = new BakeMuttonCommand(barbecuer);// 烤羊肉串
        BakeChickenWingCommand bakeChickenWingCommand = new BakeChickenWingCommand(barbecuer);// 烤鸡翅
        Waiter waiter = new Waiter(); // 服务员

        System.out.println("开门营业，顾客点菜");
        waiter.setOrder(bakeMuttonCommand);
        waiter.setOrder(bakeMuttonCommand);
        waiter.setOrder(bakeMuttonCommand);
        waiter.setOrder(bakeMuttonCommand);
        waiter.setOrder(bakeMuttonCommand);

        waiter.cancelOrder(bakeMuttonCommand);

        System.out.println("点菜完毕，通知厨房烧菜");
        waiter.notifyCommand();

    }
}
