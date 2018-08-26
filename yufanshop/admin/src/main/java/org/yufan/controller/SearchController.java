package org.yufan.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.bean.SearchResult;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public Result search(@RequestParam("q")String query,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "60")Integer rows){
        if(StringUtils.isEmpty(query)){
            return ResultUtils.buildFail(400,"条件不能为空");
        }
        try {
            SearchResult searchResult=searchService.search(query,page.longValue(),rows);
            return ResultUtils.buildSuccess(searchResult);
        } catch (SolrServerException e) {
            e.printStackTrace();
            return ResultUtils.buildFail(500,e.getMessage());
        }

    }
}
