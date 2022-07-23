package com.cycas.controller;

import com.cycas.pojo.CommonResult;
import com.cycas.pojo.Order;
import com.cycas.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public CommonResult create(Order order) {

        try {
            orderService.create(order);
            return new CommonResult(200, "订单创建成功", order);
        } catch (Exception e) {
            logger.error("订单创建失败!", e);
            return new CommonResult(500, "订单创建失败");
        }

    }

}
