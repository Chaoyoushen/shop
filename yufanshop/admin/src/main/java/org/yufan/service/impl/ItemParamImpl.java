package org.yufan.service.impl;


import org.springframework.stereotype.Service;
import org.yufan.bean.ItemParam;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.service.ItemParamService;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamImpl extends BaseServiceImpl<ItemParam> implements ItemParamService {

    @Override
    public Result getItemParamByCid(long cid) {
        ItemParam itemParam=new ItemParam();
        itemParam.setItemCatId(cid);
        List<ItemParam> list= queryListByCondition(itemParam);
        if(list!=null&&list.size()>0){
            return ResultUtils.buildSuccess(list.get(0));
        }
        return null;
    }

    @Override
    public Result insertItemParam(ItemParam itemParam) {
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        save(itemParam);
        return ResultUtils.buildSuccess();

    }
}
