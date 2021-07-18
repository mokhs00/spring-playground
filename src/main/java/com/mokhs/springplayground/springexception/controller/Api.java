package com.mokhs.springplayground.springexception.controller;

import com.mokhs.springplayground.springexception.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/v1/user")
@Validated
public class Api {

    @GetMapping
    public User get(@RequestParam @NotBlank String name,
                    @RequestParam @Min(1) Integer age) {
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
