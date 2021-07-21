package com.mokhs.springplayground.spring_filter_interceptor.controller;


import com.mokhs.springplayground.spring_filter_interceptor.annotation.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
@Auth
public class PrivateController {


    @GetMapping("/hello")
    public String hello() {
        return "private Hello";
    }
}
