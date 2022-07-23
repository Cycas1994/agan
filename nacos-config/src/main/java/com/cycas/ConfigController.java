package com.cycas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache}")
    private String useLocalCache;

    @RequestMapping("/get")
    public String get() {
        return useLocalCache;
    }

    @RequestMapping("/testDevSoft")
    public String test() {
        return useLocalCache;
    }

    @RequestMapping("/mixed")
    public String mixed() {
        return useLocalCache;
    }

    @RequestMapping("/hardReset")
    public String hard() {
        return useLocalCache;
    }
}
