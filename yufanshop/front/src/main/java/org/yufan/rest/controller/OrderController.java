package org.yufan.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yufan.bean.CartItem;
import org.yufan.bean.Order;
import org.yufan.common.JsonUtils;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.rest.mapper.CartMapper;
import org.yufan.rest.service.CartService;
import org.yufan.rest.service.OrderService;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addOrder(Long cart_id,Long user_id){
        CartItem cart = cartService.getCartById(cart_id);

        Order order=new Order();
        order.setUser_id(user_id);
        order.setPayment(cart.getPrice());
        order.setIds(""+cart.getItem_id());
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Result getOrderList(String token){
        return orderService.queryOrderList(token);
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Result updateOrder( String order_id){
        return orderService.delOrder(order_id);
    }

    @RequestMapping(value = "/pay",method =RequestMethod.POST)
    @ResponseBody
    public Result pay(String token,String order_id){
        Order order = orderService.queryOrderById(order_id);
        if(order == null){
            return ResultUtils.buildFail(100,"未查询到该订单:order_id = "+order_id);
        }
        //删除购物车中订单已结算物品
        String[] ids = order.getIds().split(",");
        for(String s:ids)
        {
            cartService.deleteByCartId(s);
        }
        return orderService.pay(token,order_id);
    }
}
