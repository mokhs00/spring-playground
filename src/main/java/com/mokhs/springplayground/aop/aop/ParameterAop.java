package com.mokhs.springplayground.aop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParameterAop {


    @Pointcut("execution(* com.mokhs.springplayground.aop.api..*.*(..))")
    private void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("===============METHOD CALLED==================");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature);

        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            System.out.print("type : '" + obj.getClass().getSimpleName() + "'");
            System.out.println(" | value : '" + obj + "'");
        }

    }


    @AfterReturning(value = "pointCut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("==================RETURN=====================");
        System.out.println("return : '" + returnObj + "'");
        System.out.println("=============================================");


    }

}
