package org.yufan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yufan.bean.CartItem;
import org.yufan.bean.Item;
import org.yufan.mapper.CartMapper;
import org.yufan.mapper.ItemMapper;
import org.yufan.service.CartService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addItemToCart(Long itemId, Long userId,Long num) {
        Item item=itemMapper.selectByPrimaryKey(itemId);
        if(item==null) return;

        CartItem cartItem=new CartItem();
        cartItem.setItem_id(itemId);
        cartItem.setUser_id(userId);
        CartItem cart=cartMapper.selectOne(cartItem);
        if(cart==null){
            cart=new CartItem();
            cart.setItem_id(itemId);
            cart.setUser_id(userId);
            cart.setNum(num);
            cart.setImage(item.getImage().split(",")[0]);
            cart.setPrice(item.getPrice());
            cart.setTitle(item.getTitle());
        }else{
            cart.setNum(cart.getNum()+1);
        }
        cartMapper.insert(cart);
    }

    @Override
    public List<CartItem> queryCartList(Long userId) {
        Example example=new Example(CartItem.class);
        example.setOrderByClause("id ASC");
        example.createCriteria().andEqualTo("user_id",userId);
        List<CartItem> carts=cartMapper.selectByExample(example);
        return carts;
    }

    @Override
    public void clearCart(Long userId) {
        Example example=new Example(CartItem.class);
        example.createCriteria().andEqualTo("user_id",userId);
        cartMapper.deleteByExample(example);
    }

    @Override
    public void delCartItem(Long cartId) {
        cartMapper.deleteByPrimaryKey(cartId);
    }
}
