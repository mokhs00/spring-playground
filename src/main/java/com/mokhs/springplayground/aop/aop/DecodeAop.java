package com.mokhs.springplayground.aop.aop;

import com.mokhs.springplayground.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {


    @Pointcut("execution(* com.mokhs.springplayground.aop.api.RestApi.*(..))")
    private void pointCut() {

    }


    @Pointcut("@annotation(com.mokhs.springplayground.aop.annotation.Decode)")
    private void enableDecode() {

    }

    @Before("pointCut() && enableDecode()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof User) {
                User user = User.class.cast(arg);
                String base64Username = user.getUsername();
                String username = new String(Base64.getDecoder().decode(base64Username), StandardCharsets.UTF_8);
                user.setUsername(username);
            }
        }


    }

    @AfterReturning(value = "pointCut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof User) {
                User user = User.class.cast(arg);
                String base64Username = user.getUsername();
                String username = Base64.getEncoder().encodeToString(base64Username.getBytes());
                user.setUsername(username);
                System.out.println(user);

            }
        }

    }
}
