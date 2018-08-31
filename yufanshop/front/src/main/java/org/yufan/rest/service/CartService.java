/*package org.yufan.rest.service;

import org.yufan.common.Result;
import org.yufan.bean.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {
    void addCartItem(Long itemid, Long num, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response);
    Result delCartItem(Long itemId,HttpServletRequest request,HttpServletResponse response);

    public void clearCart(Long userId);
}*/
package org.yufan.rest.service;

import org.yufan.bean.CartItem;

import java.util.List;

public interface CartService{
    public void deleteByCartId(String id);
    public CartItem getCartById(Long id);
}
