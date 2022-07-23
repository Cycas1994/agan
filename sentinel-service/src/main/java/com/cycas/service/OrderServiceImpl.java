package com.cycas.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @SentinelResource(value = "getOrder", blockHandler = "handleException",
            fallback = "fallback")
    public String getOrder() {
        int i = 1/0;
        return "getOrder";
    }

    public String handleException(BlockException ex) {

        return "服务不可用";
    }

    public String fallback() {

        return "出错了";
    }
}
