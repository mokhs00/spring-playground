package com.mokhs.springplayground.aop.api;

import com.mokhs.springplayground.aop.annotation.Decode;
import com.mokhs.springplayground.aop.annotation.Timer;
import com.mokhs.springplayground.aop.dto.User;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RestApi {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {

        return id + " " + name;

    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {

        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        Thread.sleep(1000 * 2);
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user) {
        return user;
    }
}
