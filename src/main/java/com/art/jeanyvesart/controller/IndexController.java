package com.art.jeanyvesart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class IndexController {
    @GetMapping
    public String gotoHomepage(){
        return "index";
    }
    @GetMapping("/about")
    public String gotoAbout(){
        return "about";
    }
}
