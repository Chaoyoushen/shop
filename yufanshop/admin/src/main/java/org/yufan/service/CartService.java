package org.yufan.service;

import org.yufan.bean.CartItem;

import java.util.List;

public interface CartService {

    public void addItemToCart(Long itemId,Long userId,Long num);

    public List<CartItem> queryCartList(Long userId);

    public void clearCart(Long userId);

    public void delCartItem(Long cartId);


}
