package org.yufan.rest.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;
import org.yufan.common.HttpClientUtil;
import org.yufan.common.JsonUtils;
import org.yufan.exception.MyException;
import org.yufan.rest.service.ItemService;
import org.yufan.exception.MyException;
import org.yufan.rest.service.JedisService;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemServiceImpl implements ItemService {

    private static final String ITEM_URL="http://admin.chaoy.com/rest/rpc/item/";
    private static final String ITEM_DESC_URL="http://admin.chaoy.com/rest/rpc/item/desc/";
    @Autowired
    JedisService jedisService;

    @Override
    public Item queryItemById(Long itemId) throws MyException,IOException {
        Map param =new HashMap<>();
        param.put("itemId",itemId+"");
        String json = jedisService.hget("item",itemId+"");
        if(StringUtils.isEmpty(json)){
            json=HttpClientUtil.doPost(ITEM_URL,param);
            jedisService.hset("item",itemId+"",json);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readTree(json);
        JsonNode data=jsonNode.get("data");
        String item_data =data.toString();
        return JsonUtils.jsonToPojo(item_data,Item.class);
    }

    @Override
    public ItemDesc queryItemDescById(Long itemId) throws MyException,IOException {
        Map param =new HashMap<>();
        param.put("itemId",itemId+"");
        String json = jedisService.hget("itemDesc",itemId+"");
        if(StringUtils.isEmpty(json)){
            json=HttpClientUtil.doPost(ITEM_DESC_URL+itemId);
            jedisService.hset("itemDesc",itemId+"",json);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readTree(json);
        JsonNode data=jsonNode.get("data");
        String item_desc_data=data.toString();
        return JsonUtils.jsonToPojo(item_desc_data,ItemDesc.class);
    }
}
