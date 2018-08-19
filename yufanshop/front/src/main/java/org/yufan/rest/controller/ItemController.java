package org.yufan.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;
import org.yufan.exception.CustomerHandlerException;
import org.yufan.exception.MyException;
import org.yufan.rest.service.ItemService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemController {

    @Autowired
    private ItemService itemService;
    @RequestMapping(value="/{itemId}",method = RequestMethod.GET)
    public  String toItem(@PathVariable("ItemId")Long itemId, Model model) throws IOException, MyException {
        Item item =itemService.queryItemById(itemId);
        ItemDesc itemDesc=itemService.queryItemDescById(itemId);
        model.addAttribute("item",item);
        String image=item.getImage();
        if(StringUtils.isEmpty(image)){
            throw new MyException("商品图片不存在");
        }
        String[] images=image.split(",");
        model.addAttribute("images",images);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }


}
