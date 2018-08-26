package org.yufan.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.yufan.bean.SearchResult;

public interface SearchDao {
    public SearchResult search(SolrQuery query) throws  SolrServerException;
}
