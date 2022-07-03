package com.cycas.transaction.controller;

import com.cycas.transaction.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyControllerTest {

    @Autowired
    private MyService myService;

    @Test
    void transactional() {
        myService.transactional();
    }
}