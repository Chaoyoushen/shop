package org.yufan.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.common.Result;
import org.yufan.service.SolrServive;

import java.io.IOException;

@Controller
public class SolrItemController {
    @Autowired
    private SolrServive solrServive;

    @RequestMapping("/importall")
    @ResponseBody
    public Result importAllItems() throws IOException, SolrServerException {
        Result result=solrServive.importAllItem();
        return result;
    }

}
