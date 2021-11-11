package com.cycas.commonutil.service.account.impl;

import com.cycas.commonutil.dao.AccountDao;
import com.cycas.commonutil.pojo.account.dmo.Account;
import com.cycas.commonutil.service.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void test() {

        Account account = new Account();
        account.setUsed(123);
        accountDao.insert(account);
        for (int i = 0; i < 3; i++) {
            try {
                batchSave(i);
            } catch (Exception e) {
                logger.error("test", e);
            }
        }

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void batchSave(int i) {

        Account account = new Account();
        account.setUsed(i);
        if (i == 1) {
            throw new RuntimeException("出错啦");
        }
        accountDao.insert(account);
    }


}
