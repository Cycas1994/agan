package com.cycas.service.impl;

import com.cycas.dao.AccountDao;
import com.cycas.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, Integer money) {

        logger.info("--->storage-account扣减账户开始");
        // 用于验证超时情况下是否能够正确回滚分布式事务
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        logger.info("--->storage-account扣减账户结束");

    }
}
