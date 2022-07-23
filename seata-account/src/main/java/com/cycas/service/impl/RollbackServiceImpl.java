package com.cycas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cycas.dao.AccountDao;
import com.cycas.exception.ApiException;
import com.cycas.pojo.Account;
import com.cycas.service.RollbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class RollbackServiceImpl implements RollbackService {

    private static final Logger logger = LoggerFactory.getLogger(RollbackServiceImpl.class);

    private RollbackService rollbackService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AccountDao accountDao;

    @PostConstruct
    private void setRollbackService() {
        rollbackService = context.getBean(RollbackService.class);
    }

    @Override
    @Transactional
    public void save() {

        insertOne();
        try {
            insertTwo();
        } catch (Exception e) {
            System.out.println("捕获了异常");
        }
    }

    private void insertOne() {
        Account account = new Account();
        account.setId(1);
        accountDao.insert(account);
    }

    private void insertTwo() throws ApiException {

        throw new ApiException("error");
    }

    public static void main(String[] args) {

        String jsonStr = "{" +
                "\"corpid\":\"dingde2ae6b631f2e1d4f2c783f7214b6d69\"," +
                "\"bpmsCallBackData\":{" +
                "\"processInstanceId\":\"31068f6f-30b0-4970-9f84-8aef0f38868e\"," +
                "\"finishTime\":1622863706000," +
                "\"corpId\":\"dingde2ae6b631f2e1d4f2c783f7214b6d69\"," +
                "\"EventType\":\"bpms_task_change\"," +
                "\"businessId\":\"202106050956000553602\"," +
                "\"title\":\"冉双双提交的退费审批（测试）\"," +
                "\"type\":\"finish\"," +
                "\"result\":\"agree\"," +
                "\"createTime\":1622860223000," +
                "\"processCode\":\"PROC-D53D6CF6-BE07-41DC-8D16-4F0C5514595E\"," +
                "\"bizCategoryId\":\"\"," +
                "\"staffId\":\"114034500935901803\"," +
                "\"taskId\":68908377877" +
                "}" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        logger.info(jsonObject.getJSONObject("bpmsCallBackData").getString("processInstanceId"));
    }


}
