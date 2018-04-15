package com.viscum.chapter03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {


    @RequestMapping(value = "/index")
    public String index(ModelMap map) {
        map.addAttribute("host","www.baidu.com");
        return "index";
    }

}
