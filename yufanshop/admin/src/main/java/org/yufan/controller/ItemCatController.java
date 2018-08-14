package org.yufan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.bean.ItemCat;
import org.yufan.service.ItemCatService;

@RequestMapping("/cat")
@Controller
public class ItemCatController {

   @Autowired
   private ItemCatService itemCatService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<ItemCat> list(@RequestParam(value ="id",defaultValue = "0") Long parentId) {

        ItemCat record=new ItemCat();
        record.setParentId(parentId);
        return itemCatService.queryListByCondition(record);
    }


}
