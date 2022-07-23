package com.cycas;

import com.cycas.service.AccountService;
import com.cycas.service.RollbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeataAccount8003ApplicationTests {

    @Autowired
    private RollbackService rollbackService;

    @Test
    void contextLoads() {
    }

    @Test
    void testTransactional() {

        rollbackService.save();
    }

}
