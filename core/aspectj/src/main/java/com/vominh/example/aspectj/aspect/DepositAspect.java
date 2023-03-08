package com.vominh.example.aspectj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class DepositAspect {

    @Pointcut("execution(public long com.vominh.example.aspectj.BankAccount.deposit(long))")
    public void callDeposit() {
    }

    @Before("callDeposit()")
    public void before(JoinPoint jp) {
        System.out.println("Before deposit: " + jp.getArgs()[0]);
    }

    @Around("callDeposit()")
    public long around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Start deposit: " + pjp.getArgs()[0]);
        return (long) pjp.proceed();
    }

    @After("callDeposit()")
    public void after(JoinPoint joinPoint) {
        System.out.println("After deposit : " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "callDeposit()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, long result) {
        System.out.println("After return method : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
    }
}
