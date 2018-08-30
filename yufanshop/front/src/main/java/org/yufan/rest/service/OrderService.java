package org.yufan.rest.service;

import org.yufan.bean.Order;

import org.yufan.common.Result;


public interface OrderService {
    Result addOrder(Order order);

    Result queryOrderList(String token);

    Result delOrder(String order_id);

    Result pay(String token,String order_id);
}
