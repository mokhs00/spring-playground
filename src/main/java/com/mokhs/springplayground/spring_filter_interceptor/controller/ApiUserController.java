package com.mokhs.springplayground.spring_filter_interceptor.controller;

import com.mokhs.springplayground.spring_filter_interceptor.dto.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/temp")
public class ApiUserController {

    @PostMapping("user")
    public User user(@RequestBody User user) {

        return user;
    }
}
