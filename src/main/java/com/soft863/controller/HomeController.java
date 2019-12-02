package com.soft863.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/hi")
    public String homePage() {
        return "login1.html";
    }

    @RequestMapping("/ha")
    public String homePage1() {
        return "mapdemo.html";
    }
}
