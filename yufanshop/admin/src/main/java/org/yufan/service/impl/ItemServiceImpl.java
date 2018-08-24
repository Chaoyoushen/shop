package org.yufan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;
import org.yufan.service.ItemDescService;
import org.yufan.service.ItemService;

import java.util.Date;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemDescService itemDescService;

    @Override
    public void addItem(Item item, String desc) {

        //保存商品
        item.setStatus(2);//设置商品的状态为下架
        item.setUpdated(new Date());
        item.setCreated(item.getUpdated());
        this.save(item);

        //保存商品详情
        ItemDesc itemDesc=new ItemDesc();
        //设置商品id
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(item.getCreated());
        itemDesc.setCreated(item.getUpdated());
        itemDescService.save(itemDesc);

    }

    @Override
    public void updateItem(Item item, String desc) {

        item.setUpdated(new Date());

        ItemDesc itemDesc = itemDescService.queryById(item.getId());
        itemDesc.setUpdated(item.getUpdated());
        itemDesc.setItemDesc(desc);
        this.update(item);
        itemDescService.update(itemDesc);
    }

    @Override
    public void updateItemStatus(Long[] ids, int status) {
        for(Long id:ids) {
            Item item = this.queryById(id);
            item.setUpdated(new Date());
            item.setStatus(status);
            this.update(item);
        }
    }
}
