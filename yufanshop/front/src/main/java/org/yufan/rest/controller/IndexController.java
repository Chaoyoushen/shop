package org.yufan.rest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/user/{page}.html")
    public String toPage(@PathVariable("page")String page){
        return page;
    }



}
