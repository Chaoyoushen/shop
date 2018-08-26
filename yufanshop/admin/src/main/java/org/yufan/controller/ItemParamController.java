package org.yufan.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.bean.Item;
import org.yufan.bean.ItemParam;
import org.yufan.common.Result;
import org.yufan.result.EasyUIResult;
import org.yufan.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/{itemCatId}")
    @ResponseBody
    public Result getItemParamByCid(@PathVariable Long itemCatId){
        Result result=itemParamService.getItemParamByCid(itemCatId);
        return result;
    }
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public Result  insertItemParam(@PathVariable Long cid,String paramData){
        ItemParam itemParam=new ItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        return itemParamService.insertItemParam(itemParam);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public EasyUIResult list(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "rows",defaultValue = "5")Integer rows){

        PageInfo<ItemParam> pageInfo=itemParamService.queryPageListByCondition(page,rows,null);

        EasyUIResult easyUIResult=new EasyUIResult((int)pageInfo.getTotal(),pageInfo.getList());
        return easyUIResult;
    }
}
