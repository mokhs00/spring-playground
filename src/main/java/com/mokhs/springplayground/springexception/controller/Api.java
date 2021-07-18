package com.mokhs.springplayground.springexception.controller;

import com.mokhs.springplayground.springexception.dto.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class Api {

    @GetMapping
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        return user;
    }

    @PostMapping
    public User post(@RequestBody @Valid User user) {
        System.out.println(user);

        return user;
    }


}
