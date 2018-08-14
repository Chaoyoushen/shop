package org.yufan.controller;

import java.util.*;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.result.EasyUIResult;
import org.yufan.service.ItemDescService;
import org.yufan.service.ItemService;

@RequestMapping("/item")
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result itemAdd(Item item, String desc){
        itemService.addItem(item,desc);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result  itemUpdate(Item item,String desc){
        itemService.updateItem(item,desc);
        return ResultUtils.buildSuccess();
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result itemDelete(@RequestParam("ids")Long[] ids) {
        itemService.updateItemStatus(ids,2);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping(value = "/instock",method = RequestMethod.POST)
    @ResponseBody
    public Result itemInstock(@RequestParam("ids")Long[] ids) {
        itemService.updateItemStatus(ids,2);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping(value = "/reshelf",method = RequestMethod.POST)
    @ResponseBody
    public Result itemReshelf(@RequestParam("ids")Long[] ids) {
        itemService.updateItemStatus(ids,1);
        return ResultUtils.buildSuccess();
    }







    @RequestMapping(value = "/list")
    @ResponseBody
    public EasyUIResult list(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "rows",defaultValue = "5")Integer rows){

        PageInfo<Item> pageInfo = itemService.queryPageListByCondition(page, rows, null);

        EasyUIResult easyUIResult=new EasyUIResult((int)pageInfo.getTotal(),pageInfo.getList());
        return easyUIResult;
    }

    @RequestMapping(value = "/desc/{itemId}")
    @ResponseBody
    public ItemDesc   queryItemDesc(@PathVariable Long itemId){

       
        return itemDescService.queryById(itemId);

    }


}
