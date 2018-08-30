package org.yufan.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.Item;
import org.yufan.bean.Order;
import org.yufan.bean.User;
import org.yufan.common.*;
import org.yufan.rest.mapper.OrderMapper;
import org.yufan.rest.mapper.UserMapper;
import org.yufan.rest.service.JedisService;
import org.yufan.rest.service.OrderService;


import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

private String CHECK_URL="http://sso.chaoyous.cn/check";
private String ITEM_URL="http://admin.chaoyous.cn/rest/rpc/item/";
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private JedisService jedisService;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Result addOrder(Order order) {
        String[] itemIds=order.getIds().split(",");
        List<Item> itemList =new ArrayList<Item>();

        for(String id:itemIds){
            String json=HttpClientUtil.doGet(ITEM_URL+id);
            Result result=JsonUtils.jsonToPojo(json,Result.class);
            String data=JsonUtils.objectToJson(result.getData());
            Item item=JsonUtils.jsonToPojo(data,Item.class);
            itemList.add(item);
        }
        order.setStatus(new Long(1));
        order.setTitle(itemList.get(0).getTitle());
        order.setImage(itemList.get(0).getImage());
        order.setCount(new Long(itemList.size()));
        order.setOrder_id(StringUtils.join(System.currentTimeMillis(),new Random().nextInt(10)+""));
        order.setCreate_time(new Date());
        orderMapper.insert(order);
        return ResultUtils.buildSuccess(order);
    }

    @Override
    public Result queryOrderList(String token) {
        Result result= JsonUtils.jsonToPojo(HttpClientUtil.doGet(CHECK_URL+"?token="+token),Result.class);
        if(result.getData()==null){
            return ResultUtils.buildFail(101,"please login");
        }
        Order order=new Order();



        order.setUser_id(Long.getLong(result.getData().toString()));
        return ResultUtils.buildSuccess(orderMapper.select(order));
    }
    //状态：1、未付款，2、已付款
    @Override
    public Result delOrder(String order_id) {
        Order order=new Order();
        order.setOrder_id(order_id);
        order=orderMapper.selectOne(order);
        orderMapper.delete(order);
        return ResultUtils.buildSuccess();
    }

    @Override
    public Result pay(String token, String order_id) {
        Order order=orderMapper.selectByPrimaryKey(order_id);
        String res=jedisService.get(token);
        if(StringUtils.isEmpty(token)){
            return ResultUtils.buildFail(301,"empty token");
        }
        if(res==null){
            return ResultUtils.buildFail(302,"please login");
        }
        if(order==null){
            return ResultUtils.buildFail(303,"no such order");
        }
        User user=userMapper.selectByPrimaryKey(Long.getLong(res));
        if(order.getPayment()>user.getCount()){
            return ResultUtils.buildFail(304,"余额不足");
        }
        user.setCount(user.getCount()-order.getPayment());
        order.setStatus(new Long(2));
        userMapper.updateByPrimaryKeySelective(user);
        orderMapper.updateByPrimaryKeySelective(order);
        return ResultUtils.buildSuccess();
    }
}
