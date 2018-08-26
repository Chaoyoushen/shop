package org.yufan.rest.service.impl;

import org.springframework.stereotype.Service;
import org.yufan.bean.SearchResult;
import org.yufan.common.HttpClientUtil;
import org.yufan.common.JsonUtils;
import org.yufan.rest.service.FroSearchService;

import java.util.HashMap;
import java.util.Map;

@Service
public class FroSearchServiceImpl implements FroSearchService {

    String SEARCH_URL="http://admin.yufan.com/rest/search/query";
    @Override
    public SearchResult search(String query, int page) {
        String json=HttpClientUtil.doGet(SEARCH_URL+"?q="+query+"&page="+page);
        String jsonData=json.substring(json.indexOf("data")+6,json.length()-1);
        SearchResult searchResult=JsonUtils.jsonToPojo(jsonData,SearchResult.class);
        int i=0;
        return searchResult;

    }
}
