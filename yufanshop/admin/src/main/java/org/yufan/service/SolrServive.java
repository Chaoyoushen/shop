package org.yufan.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.yufan.common.Result;

import java.io.IOException;

public interface SolrServive {
    public Result importAllItem() throws IOException, SolrServerException;
}
