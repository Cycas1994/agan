package com.cycas.commonutil.service.account;

public interface AccountService {

    void decrease(Long userId, Integer money);

    void test();

    void batchSave(int i);
}
