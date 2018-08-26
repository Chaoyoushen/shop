package org.yufan.service.impl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.Item;
import org.yufan.bean.ItemDesc;
import org.yufan.bean.SolrItem;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.mapper.ItemDescMapper;
import org.yufan.mapper.ItemMapper;
import org.yufan.mapper.SolrItemMapper;
import org.yufan.service.SolrServive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class SolrServiceImpl implements SolrServive {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;
    @Autowired
    private SolrItemMapper solrItemMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public Result importAllItem() throws IOException, SolrServerException {
        List<Item> itemList=itemMapper.selectAll();
        List<ItemDesc> descList=itemDescMapper.selectAll();
        List<SolrItem> solrItemList=new ArrayList<SolrItem>();
        SolrItem solrItem=new SolrItem();
        Item item;
        ItemDesc itemDesc;
        for(int i=0;i<itemList.size();i++){
            item=itemList.get(i);
            itemDesc=descList.get(i);
            SolrInputDocument document=new SolrInputDocument();
            document.setField("id",item.getId());
            document.setField("item_title", item.getTitle());
            document.setField("item_sell_point", item.getSellPoint());
            document.setField("item_price", item.getPrice());
            document.setField("item_image", item.getImage());
            document.setField("item_category_name", item.getCid());
            document.setField("item_desc", itemDesc.getItemDesc());
            solrServer.add(document);
        }
        solrServer.commit();
        return ResultUtils.buildSuccess();
    }
}
