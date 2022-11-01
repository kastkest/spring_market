package com.github.kastkest.spring_market.aspects;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceAspect {

    @Before("execution(public * com.github.kastkest.spring_market.services.OrderService.*(..))")
    public void beforeOrderServiceEachMethod() {
        System.out.println("AOP: что-то произошло");
    }
}
