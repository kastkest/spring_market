package com.github.kastkest.spring_market.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceAspect {

    @Around("execution(public * com.github.kastkest.spring_market.core.services.OrderService.*(..))")
    public Object durationOfAnyOrderServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("Начало метода");
        Long begin = System.currentTimeMillis();
        Object out = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        Long duration = end - begin;
        System.err.println("OrderService: " + duration +" ms.");
        System.err.println("Конец метода");
        return out;
    }


    @Around("execution(public * com.github.kastkest.spring_market.core.services.ProductsService.*(..))")
    public Object durationOfAnyProductServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("Начало метода");
        Long begin = System.currentTimeMillis();
        Object out = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        Long duration = end - begin;
        System.err.println("ProductsService: " + duration +" ms.");
        System.err.println("Конец метода");
        return out;
    }

    @Around("execution(public * com.github.kastkest.spring_market.core.services.UserService.*(..))")
    public Object durationOfAnyUserServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("Начало метода");
        Long begin = System.currentTimeMillis();
        Object out = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        Long duration = end - begin;
        System.err.println("UserService: " + duration +" ms.");
        System.err.println("Конец метода");
        return out;
    }
}
