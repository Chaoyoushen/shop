package org.yufan.rest.service;

import org.yufan.bean.SearchResult;

public interface FroSearchService {
    SearchResult search(String query,int page);
}
