package org.yufan.rest.service.impl;


import com.alibaba.druid.util.StringUtils;
import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;
import org.yufan.common.HttpClientUtil;
import org.yufan.exception.MyException;
import org.yufan.rest.service.ItemService;
import org.yufan.exception.MyException;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemServiceImpl implements ItemService {

    private static final String ITEM_URL="http://admin.chaoy.com/rest/rpc/item/";
    private static final String ITEM_DESC_URL="http://admin.chaoy.com/rest/rpc/item/desc/";

    @Override
    public Item queryItemById(Long itemId) throws IOException {
        String json = HttpClientUtil.doGet(ITEM_URL+itemId);
        if(StringUtils.isEmpty(json)){
           // throw new MyException("cc");
        }
        return null;
    }

    @Override
    public ItemDesc queryItemDescById(Long itemmId) throws IOException {
        return null;
    }
}
