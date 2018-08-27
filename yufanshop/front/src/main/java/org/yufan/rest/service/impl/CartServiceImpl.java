package org.yufan.rest.service.impl;

import org.springframework.stereotype.Service;
import org.yufan.bean.Item;
import org.yufan.common.*;
import org.yufan.bean.CartItem;
import org.yufan.rest.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private String ITEM_URL="admin.chaoyous.cn/rest/rpc/item";
    @Override
    public Result addCartItem(Long itemid, Long num, HttpServletRequest request, HttpServletResponse response) {
        CartItem cartItem=null;
        List<CartItem> itemList=getCartItemList(request,response);
        for(CartItem item:itemList){
            if(item.getId()==itemid){
                item.setNum(item.getNum()+num);
                cartItem=item;
                break;
            }
        }
        if(cartItem==null) {
            String json = HttpClientUtil.doGet(ITEM_URL + itemid);
            Result result = JsonUtils.jsonToPojo(json, Result.class);
            if (result.getStatus() == 200) {
                Item item = (Item) result.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setPrice(item.getPrice());
                cartItem.setImage(item.getImage().split(",")[0]);
                cartItem.setNum(num);
            }
        }
        CookieUtil.addCookie(response,"Cart",JsonUtils.objectToJson(itemList),-1);
        return ResultUtils.buildSuccess();
    }

    public List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response){
        String json=CookieUtil.getById(request,"Cart");
        if(json==null){
            return new ArrayList<>();
        }
        List<CartItem> list=JsonUtils.jsonToList(json,CartItem.class);
        return list;
    }

    @Override
    public Result delCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList=getCartItemList(request,response);
        for (CartItem cartItem:itemList){
            if(cartItem.getId()==itemId){
                itemList.remove(cartItem);
                break;
            }
        }
        CookieUtil.removeCookie(response,"CART");
        CookieUtil.addCookie(response,"CART",JsonUtils.objectToJson(itemList),-1);
        return ResultUtils.buildSuccess();
    }
}
