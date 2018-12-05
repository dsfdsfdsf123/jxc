package com.coder520.jxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liugang
 * @create 2018/12/5 21:24
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String root(){
        return "redirect:/login.html";
    }
}
