package org.yufan.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.yufan.bean.Item;
import  java.util.*;

public interface ItemService extends BaseService<Item>{

    public void addItem(Item item,String desc);

    public void updateItem(Item item, String desc);

    public void updateItemStatus(@RequestParam("ids ") Long[] ids, int status);
}
