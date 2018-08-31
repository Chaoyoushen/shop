package org.yufan.service;

import org.yufan.bean.CartItem;
import org.yufan.common.Result;

import java.util.List;

public interface CartService {

    public Result addItemToCart(Long itemId, Long userId, Long num);

    public List<CartItem> queryCartList(Long userId);

    public void clearCart(Long userId);

    public void delCartItem(Long cartId);


}
