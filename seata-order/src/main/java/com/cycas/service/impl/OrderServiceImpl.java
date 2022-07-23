package com.cycas.service.impl;

import com.alibaba.fastjson.JSON;
import com.cycas.dao.OrderDao;
import com.cycas.pojo.Order;
import com.cycas.service.AccountService;
import com.cycas.service.OrderService;
import com.cycas.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp_tx_group", rollbackFor = Exception.class)
    public void create(Order order) {

        logger.info("--->开始新建订单");
        logger.info("order req:{}", JSON.toJSONString(order));
        // 1.新建订单
        orderDao.insert(order);

        // 2.扣减库存
        logger.info("--->订单为服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());
        logger.info("--->订单微服务调用库存结束");

        // 3.扣减账户
        logger.info("--->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        logger.info("--->订单微服务调用账户结束");

        // 4.修改订单状态
        logger.info("--->修改订单状态");
        logger.info("orderId:{}", order.getId());
        orderDao.updateStatus(order.getId());


    }

    @Override
    public Order getOrderById(Long id) {

        return orderDao.selectByPrimaryKey(id);
    }
}
