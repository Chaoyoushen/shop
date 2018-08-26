package org.yufan.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.yufan.bean.SearchResult;
import org.yufan.rest.service.FroSearchService;

@Controller

public class FroSearchController {
    @Autowired
    private FroSearchService froSearchService;
    @RequestMapping("/search")
    public String search(@RequestParam("q")String query, @RequestParam(defaultValue = "1") Integer page, Model model){
        SearchResult searchResult=froSearchService.search(query,page);

        model.addAttribute("query",query);
        model.addAttribute("totalPages",searchResult.getPageCount());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("page",page);

        return "search";
    }
}
