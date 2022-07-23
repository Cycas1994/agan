package com.cycas.service;


import com.cycas.pojo.Order;

public interface OrderService {

    void create(Order order);

    Order getOrderById(Long id);
}
