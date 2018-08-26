package org.yufan.dao.Impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.Item;
import org.yufan.bean.SearchResult;
import org.yufan.dao.SearchDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws SolrServerException {
        SearchResult result=new SearchResult();
        QueryResponse queryResponse=solrServer.query(query);
        SolrDocumentList solrDocuments=queryResponse.getResults();
        result.setRecordCount(solrDocuments.getNumFound());
        List<Item> itemList=new ArrayList<>();
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for(SolrDocument solrDocument :solrDocuments) {
            Item item = new Item();
            item.setId((Long) solrDocument.get("id"));
            if(item==null){
                return null;
            }
            List<String> list = highlighting.get(solrDocument.get("id").toString()).get("item_title");
            String title = "";
            if (null != list && !list.isEmpty()) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSellPoint((String) solrDocument.get("item_sell_point"));
            item.setCid(Long.getLong(solrDocument.get("item_category_name").toString()));

            itemList.add(item);
        }

        result.setItemList(itemList);


        return result;



    }
}
