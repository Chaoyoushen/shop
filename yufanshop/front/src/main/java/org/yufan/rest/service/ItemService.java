package org.yufan.rest.service;

import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;

import java.io.IOException;

public interface ItemService {
    public Item queryItemById(Long itemId) throws IOException;

    public ItemDesc queryItemDescById(Long itemmId) throws IOException;

}
