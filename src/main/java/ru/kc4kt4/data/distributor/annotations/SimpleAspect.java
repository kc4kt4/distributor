package ru.kc4kt4.data.distributor.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Slf4j
@Component
public class SimpleAspect {

    @Around("@annotation(ExecutionTimeReport)")
    public Object executionTimeReport(ProceedingJoinPoint joinPoint) throws Throwable {

        final long start = Instant.now().toEpochMilli();

        final Object proceed = joinPoint.proceed();

        final long executionTime = Instant.now().toEpochMilli() - start;
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }
}
