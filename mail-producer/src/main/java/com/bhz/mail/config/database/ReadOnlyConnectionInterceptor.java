package com.bhz.mail.config.database;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ReadOnlyConnectionInterceptor implements Ordered {

    public static final Logger logger = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);


    @Around("@annotation(readOnlyConnetion)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnetion readOnlyConnetion) throws Throwable {
        try {
            logger.info("-------------------set DataBase connect 2 read only-------------------");
            DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.SLAVER);
            Object result = proceedingJoinPoint.proceed();
            return result;
        } finally {
            DataBaseContextHolder.clearDataBaseType();
            logger.info("-------------------clear database connection-------------------");
        }
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
