package org.yufan.rest.service;

import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;
import org.yufan.exception.MyException;

import java.io.IOException;

public interface ItemService {
    public Item queryItemById(Long itemId) throws MyException,IOException;

    public ItemDesc queryItemDescById(Long itemId) throws MyException,IOException;

}
