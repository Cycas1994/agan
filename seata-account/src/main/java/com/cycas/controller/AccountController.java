package com.cycas.controller;

import com.cycas.pojo.CommonResult;
import com.cycas.service.AccountService;
import com.cycas.service.RollbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RollbackService rollbackService;

    @RequestMapping("/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") Integer money) {

        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户成功");
    }

    @RequestMapping("/save")
    public CommonResult save(@RequestParam("userId") Long userId, @RequestParam("money") Integer money) {

        rollbackService.save();
        return new CommonResult(200, "成功");
    }
}
