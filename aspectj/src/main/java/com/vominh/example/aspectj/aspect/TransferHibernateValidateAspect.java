package com.vominh.example.aspectj.aspect;

import com.vominh.example.aspectj.Transaction;
import com.vominh.example.aspectj.annotation.Range;
import com.vominh.example.aspectj.exception.TransactionException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


// https://www.primefaces.org/showcase/ui/csv/bean.xhtml
@Aspect
public class TransferHibernateValidateAspect {

    @Pointcut("execution(public long com.vominh.example.aspectj.BankAccount.transfer(..))")
    public void callTransfer() {
    }

    @Pointcut("execution(public long com.vominh.example.aspectj.BankAccount.transfer(..))")
    public void callTransfer2() {
    }

    @Before("callTransfer() && @annotation(com.vominh.example.aspectj.annotation.Check)")
    public void validateTransaction(JoinPoint joinPoint) {
        System.out.println("!!!!! Hibernate validate start !!!!!");
        Transaction transaction = (Transaction) joinPoint.getArgs()[0];

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var violations = validator.validate(transaction);

        for (var violation : violations) {
            System.err.println(violation.getPropertyPath().toString() + " " + violation.getMessage());
        }

    }

    @Before("callTransfer2() && @annotation(com.vominh.example.aspectj.annotation.Check)")
    public void validateTransaction2(JoinPoint joinPoint) {
        System.out.println("!!!!! Hibernate validate start !!!!!");
        Transaction transaction = (Transaction) joinPoint.getArgs()[0];

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var violations = validator.validate(transaction);

        for (var violation : violations) {
            System.err.println(violation.getPropertyPath().toString() + " " + violation.getMessage());
        }

    }
}
