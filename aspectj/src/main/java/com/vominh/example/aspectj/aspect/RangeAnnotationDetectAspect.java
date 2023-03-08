package com.vominh.example.aspectj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class RangeAnnotationDetectAspect {

//    @Pointcut("@annotation(com.vominh.example.aspectj.annotation.Range)")
//    public void rangeAnnotation() {
//    }
//
//
//    @Before("rangeAnnotation()")
//    public void showValue(JoinPoint joinPoint) {
//        System.out.println("Field " + joinPoint.getSignature().getName() + " touched");
//    }
}
