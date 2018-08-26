package org.yufan.service;

import org.yufan.bean.ItemParam;
import org.yufan.common.Result;

public interface ItemParamService extends BaseService<ItemParam>{



        public Result getItemParamByCid(long cid);

        public Result insertItemParam(ItemParam itemParam);

}
