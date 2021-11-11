package com.cycas.commonutil.controller;

import com.cycas.commonutil.pojo.common.JsonData;
import com.cycas.commonutil.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/test")
    public JsonData test() {

        accountService.test();
        return JsonData.buildSuccess("success");

    }
}
