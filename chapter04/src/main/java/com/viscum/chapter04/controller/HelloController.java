package com.viscum.chapter04.controller;

import com.viscum.chapter04.exceptionHandle.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @RequestMapping(value = "/index")
    public void index() throws Exception {
        throw new Exception("报错了！！！！");
    }
    @RequestMapping(value = "/myException")
    public void myException() throws Exception {
        throw new MyException("发生异常!!!!");
    }

}
