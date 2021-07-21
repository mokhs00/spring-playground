package com.mokhs.springplayground.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {


    // aop 기반 프록시로 작동 -> public method 만 가능
    // 같은 클래스 내에서 같은 메서드를 호출할 때는 async 를 타지 않으니 주의
    // db에서는 트랙잭션 단위로 작동하기 때문에 async로 동작할 수 없으니 상황에 맞게 사용ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ
    @Async("async-thread")
    public CompletableFuture run() {
        return new AsyncResult(hello()).completable();
    }

    public String hello() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                log.info("thread sleep ...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "hello";
    }
}
