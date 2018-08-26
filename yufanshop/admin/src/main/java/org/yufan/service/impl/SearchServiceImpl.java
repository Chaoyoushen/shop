package org.yufan.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.SearchResult;
import org.yufan.dao.SearchDao;
import org.yufan.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {


    @Autowired
    private SearchDao searchDao;
    @Override
    public SearchResult search(String query, Long page, int rows) throws SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery(query);
        solrQuery.setStart((int)(page-1)*rows);
        solrQuery.setRows(rows);
        solrQuery.set("df","item_keywords");
        solrQuery.setHighlight(true);
        solrQuery.set("id");
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"coler:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        SearchResult searchResult=searchDao.search(solrQuery);
        Long recordCount =searchResult.getRecordCount();
        Long pageCount = recordCount/rows;
        if(recordCount%rows>0){
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurrentPage(page);
        return searchResult;
    }
}
