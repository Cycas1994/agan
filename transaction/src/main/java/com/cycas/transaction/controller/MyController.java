package com.cycas.transaction.controller;

import com.cycas.transaction.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping("/transactional")
    public String transactional() {
        myService.transactional();
        return "ok";
    }
}
