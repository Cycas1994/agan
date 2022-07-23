package com.cycas;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cycas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/testA")
    public String testA() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "testB";
    }

    @GetMapping("/testThreadCnt")
    public String testThreadCnt() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testThreadCnt";
    }

    @GetMapping("/testLianLu1")
    public String testLianLu1() {

        return orderService.getOrder();
    }

    @GetMapping("/testLianLu2")
    public String testLianLu2() {
        return orderService.getOrder();
    }
}
