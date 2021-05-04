package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Performance {

    @Pointcut("execution(* com.example.board.BoardService.getBoards(..))")
    public void getBoards(){}

    @Pointcut("execution(* com.example.user.UserServiceImpl.getUsers(..))")
    public void getUsers(){}

    @Around("getBoards() || getUsers()")
    public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            System.out.println("[AOP] 수행 시간: " + (end-start));
        } catch (Throwable throwable) {
            System.out.println("[AOP] exception! " + throwable.getMessage());
        }

        return result;
    }
}
