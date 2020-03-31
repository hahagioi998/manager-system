package com.swpu.resource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToIndexController {

    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
}
