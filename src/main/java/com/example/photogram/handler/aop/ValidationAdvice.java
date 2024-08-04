package com.example.photogram.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAdvice {

    @Around("execution(* com.example.photogram.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        System.out.println("web api 컨트롤러===============================");
        // proceedingJoinPoint => profile 함수의 모든 곳에 접근할 수 있는 변수
        // ex `userController`의 profile 함수보다 먼저 실행

        return proceedingJoinPoint.proceed(); // profile 함수가 실행됨
    }

    @Around("execution(* com.example.photogram.controller.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("web 컨트롤러===============================");
        return proceedingJoinPoint.proceed();
    }
}
