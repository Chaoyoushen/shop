/*package org.yufan.rest.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String ITEM_URL="http://admin.chaoyous.cn/rest/rpc/item/";


    @Autowired
    //private ItemMapper itemMapper;
    @Override
    public void addCartItem(Long itemid, Long num, HttpServletRequest request, HttpServletResponse response) {
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
            if(json==null){
                return ;
            }

            //json = json.replace("\"", "\\\"");
            //String jsonData=json.substring(json.indexOf("data")+6,json.length()-1);
            //jsonData = jsonData.replace("\"", "\\\"");
            //Result result = JsonUtils.jsonToPojo(json, Result.class);
            //Item item=(Item)JsonUtils.jsonToPojo(jsonData,Item.class);
            //if(item==null){
            //    return ResultUtils.buildFail(100,"not exist item");
            //}
            //   cartItem.setId(item.getId());
            //    cartItem.setTitle(item.getTitle());
            //    cartItem.setPrice(item.getPrice());
            //   cartItem.setImage(item.getImage().split(",")[0]);
            //    cartItem.setNum(num);
            return;
        }
        //CookieUtil.addCookie(response,"cart",JsonUtils.objectToJson(itemList),-1);
            return ;
    }

    public List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response){
        String json=CookieUtil.getById(request,"cart");
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

    @Override
    public void clearCart(Long userId) {

    }
}*/
package org.yufan.rest.service.impl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.Item;
import org.yufan.common.*;
import org.yufan.bean.CartItem;
import org.yufan.rest.mapper.CartMapper;
import org.yufan.rest.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Override
    public void deleteByCartId(String id) {
        cartMapper.deleteByPrimaryKey(Long.parseLong(id));
    }

    @Override
    public CartItem getCartById(Long id) {
        return cartMapper.selectByPrimaryKey(id);
    }
}