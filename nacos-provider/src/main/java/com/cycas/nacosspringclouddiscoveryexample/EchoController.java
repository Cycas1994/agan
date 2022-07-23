package com.cycas.nacosspringclouddiscoveryexample;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.*;

@RestController
public class EchoController {

    @RequestMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @GetMapping("/hi")
    @SentinelResource(value="hi")
    public String hi(@RequestParam(value = "name",defaultValue = "forezp",required = false) String name) {

        return "hi "+name;
    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public String byResource() {
        return "按资源名称限流";
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public String byUrl() {
        return "按url限流";
    }

    public String handleException(BlockException exception) {
        return "自定义限流信息";
    }

    @GetMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler", blockHandler = "handleException", blockHandlerClass = CustomBlockHandler.class)
    public String blockHandler() {
        return "限流成功";
    }
}