package com.jiehfut.bspringbootinitializr.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/testinitializr")
    public String testinitializr() {
        return "testinitializr";
    }



}
