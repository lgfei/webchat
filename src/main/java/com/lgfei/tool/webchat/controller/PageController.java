package com.lgfei.tool.webchat.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/page")
public class PageController
{
    private static Logger log = Logger.getLogger(PageController.class);
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage()
    {
        log.info("enter PageController.indexPage");
        return "index";
    }
    
    @RequestMapping(value = "/chatroom", method = RequestMethod.GET)
    public String chatroomPage()
    {
        log.info("enter PageController.chatroomPage");
        return "chatroom";
    }
}
