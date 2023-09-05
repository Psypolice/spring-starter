package com.sharov.logging.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class CommonPointcuts {
    //@within - check annotation on the class lvl
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    //with in - check class type name
    @Pointcut("within(com.sharov.*.service.*)")
    public void isServiceLayer() {
    }
}
