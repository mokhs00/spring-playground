package com.mokhs.springplayground.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.mokhs.springplayground.aop.api.RestApi.*(..))")
    private void pointCut() {

    }

    @Pointcut("@annotation(com.mokhs.springplayground.aop.annotation.Timer)")
    private void enableTimer() {

    }


    @Around("pointCut() && enableTimer()")
    private void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
    }


}
