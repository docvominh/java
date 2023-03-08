package com.vominh.example.aspectj.aspect;

import com.vominh.example.aspectj.Transaction;
import com.vominh.example.aspectj.exception.TransactionException;
import com.vominh.example.aspectj.annotation.Range;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TransferAspect {

    @Pointcut("execution(public long com.vominh.example.aspectj.BankAccount.transfer(..))")
    public void callTransfer() {
    }

    @Before("callTransfer() && @annotation(com.vominh.example.aspectj.annotation.Check)")
    public void validateTransaction(JoinPoint joinPoint) {
        Transaction transaction = (Transaction) joinPoint.getArgs()[0];
        System.out.println("BEFORE TRANSFER:" + transaction.getAmount());

        var fields = Transaction.class.getDeclaredFields();

        for (var field : fields) {
            if (field.isAnnotationPresent(Range.class)) {
                var min = field.getAnnotation(Range.class).min();
                var max = field.getAnnotation(Range.class).max();

                if (transaction.getAmount() < min || transaction.getAmount() > max) {
                    throw new TransactionException(String.format("Amount invalid, must between %s and %s", min, max));
                }
            }
        }
    }
}
