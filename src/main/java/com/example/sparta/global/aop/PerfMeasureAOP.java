package com.example.sparta.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerfMeasureAOP {

  @Pointcut("@within(MyPerfMeasure) || @annotation(MyPerfMeasure)")
  public void perfPoint() {
  }

  @Around("perfPoint()")
  public void perfMeasureAOP(ProceedingJoinPoint pjp) throws Throwable{
    long begin = System.currentTimeMillis();
    System.out.println("시간 측정 시작");
    pjp.proceed();
    long time = System.currentTimeMillis() - begin;
    System.out.println("소요시간 : " + time);
  }
}
