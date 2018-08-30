package org.yufan.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yufan.bean.Order;
import org.yufan.common.Result;
import org.yufan.rest.service.OrderService;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addOrder(String item_ids,Long payment,Long user_id){
        Order order=new Order();
        order.setUser_id(user_id);
        order.setPayment(payment);
        order.setIds(item_ids);
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
        return orderService.pay(token,order_id);
    }
}
