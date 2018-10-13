package com.zhiyun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
public class TestController {

    @RequestMapping(value = "doList", method = {RequestMethod.POST})
    public void doList() {
    }

}
