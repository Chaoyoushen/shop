package org.yufan.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.yufan.bean.SearchResult;

public interface SearchService {
    SearchResult search(String query,Long page,int rows) throws SolrServerException;

}
