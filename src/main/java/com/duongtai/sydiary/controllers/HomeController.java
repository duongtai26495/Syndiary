package com.duongtai.sydiary.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String homePage(){
        return "Hello Sydiary";
    }
}
