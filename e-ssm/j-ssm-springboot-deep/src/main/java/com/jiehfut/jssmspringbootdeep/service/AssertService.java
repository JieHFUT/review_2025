package com.jiehfut.jssmspringbootdeep.service;


import org.springframework.stereotype.Service;

@Service
public class AssertService {

    public String Assert() {
        System.out.println("assert...");
        return "assert....";
    }

}
