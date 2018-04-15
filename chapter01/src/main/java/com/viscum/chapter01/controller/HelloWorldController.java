package com.viscum.chapter01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @RestController = @ResponseBody ＋ @Controller
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @RequestMapping("/index.do")
    public String index(){
        System.out.println("Hello World!!!");
        return "Hello World";
    }
}
