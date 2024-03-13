package com.art.jeanyvesart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Slf4j
//@RequestMapping("/contact")
public class ContactController {
@GetMapping("/contact")
    public String getContactPage(){
        return "contact";
    }
}
