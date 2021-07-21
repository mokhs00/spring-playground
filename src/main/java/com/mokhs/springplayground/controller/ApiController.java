package com.mokhs.springplayground.controller;

import com.mokhs.springplayground.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {


    private final AsyncService asyncService;

    @GetMapping("/api/hello")
    public CompletableFuture hello() {

        log.info("method hello");
        return asyncService.run();

    }
}
