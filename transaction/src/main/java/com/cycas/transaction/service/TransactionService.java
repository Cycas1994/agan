package com.cycas.transaction.service;

public interface TransactionService {
    void required();
    void requiredNew();
    void nested();
    void notSupported();
}
