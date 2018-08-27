package org.yufan.service;

import org.yufan.bean.CartItem;
import org.yufan.bean.Order;
import org.yufan.common.Result;

public interface OrderService {
    Result addOrder(Order order);
    Result queryOrderByUserId(Long userid);
}
