package com.cycas.nacosspringclouddiscoveryexample;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomBlockHandler {

    public String handleException(BlockException e) {
        return "自定义限流信息";
    }
}
